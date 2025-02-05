package project.libraryserver.Server;

import javafx.application.Platform;
import org.json.JSONArray;
import org.json.JSONObject;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Database.MySql;
import project.libraryserver.Models.GenerateJson;
import project.libraryserver.Models.JsonFileHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;
    private WatchService watchService;
    private long lastModifiedTime = 0;

    // Constructor
    public ClientHandler(Socket socket) throws IOException {
        clientSocket = socket;

        // get the outputstream of client
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        // get the inputstream of client
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(DATA.SERVER_LOG_FILE).getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Tạo một thread để theo dõi sự thay đổi của file log
            Thread watchThread = new Thread(this::watchBorrowJsonFile);
            watchThread.setDaemon(true);
            watchThread.start();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // Bỏ qua dòng trống
                }
                JSONObject json = null;

                try {
                    json = new JSONObject(line);
                } catch (Exception e) {
                    ServerLog.getInstance().writeLog("Invalid json received.");
                }

                if (json != null) {
                    HandlerUserRequest(json);
                } else {
                    System.out.println("Failed to load json or wrong json format");
                }

            }
        }
        catch (IOException e) {
//            e.printStackTrace(System.out);
            ServerLog.getInstance().writeLog("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void SendMessage(JSONObject json) {
        System.out.println(json);
        out.println(json);
        out.flush();
    }

    // bắt json request từ client
    private void HandlerUserRequest(JSONObject json) throws SQLException {
        JsonType type = JsonType.valueOf(json.getString("type"));
//        System.out.println(json);
        switch (type) {
            case NORMAL_LOGIN -> ServerResponseNormalLogin(json);
            case GOOGLE_LOGIN -> ServerResponseGoogleLogin(json);
            case NORMAL_REGISTER -> ServerResponseNormalRegister(json);
            case GOOGLE_REGISTER -> ServerResponseGoogleRegister(json);
            case RATING_BOOK -> ServerAddNewRating(json);
            case BORROW_BOOK -> ServerAddNewBorrowBookRequest(json);
            case CHANGE_PASSWORD -> ServerChangeUserPassword(json);
            default -> throw new IllegalArgumentException("Unsupported JSON type");
        }
    }

    private void ServerResponseNormalLogin(JSONObject json) throws SQLException {
        boolean check = MySql.getInstance().CheckNormalLogin(
                json.getString("email"), json.getString("password"));
        JSONObject response;
        if (check) {
            // Ghi log thông báo phản hồi yêu cầu đăng nhập từ người dùng
            ServerLog.getInstance().writeLog(
                    "Server accept user ["
                            + json.getString("email")
                            + ",type:normal] to logging in application.");

            // Trả về json chấp nhận yêu cầu đăng nhập
            ArrayList<String> information = MySql
                    .getInstance()
                    .GetNormalUserBasicInformation(json.getString("email"));

            response = GenerateJson.ResponseLoginSuccess(
                    Integer.parseInt(information.get(0)),
                    information.get(1),
                    information.get(2),
                    information.get(3)
            );



        } else {
            // Ghi log thông báo phản hồi yêu cầu đăng nhập từ người dùng
            ServerLog.getInstance().writeLog(
                    "Server decline user ["
                            + json.getString("email")
                            + ",type:normal] to logging in application.");

            // Trả về json từ chối yêu cầu đăng nhập.
            response = GenerateJson.ResponseLoginFailed();
        }
        SendMessage(response);
    }

    private void ServerResponseGoogleLogin(JSONObject json) throws SQLException {
        boolean check = MySql.getInstance().CheckGoogleLogin(
                json.getString("id"), json.getString("email"));
        JSONObject response;
        if (check) {
            // Ghi log thông báo phản hồi yêu cầu đăng nhập từ người dùng
            ServerLog.getInstance().writeLog(
                    "Server accept user ["
                            + json.getString("email")
                            + ",type:google] to logging in application.");

            ArrayList<String> information = MySql
                    .getInstance()
                    .GetNormalUserBasicInformation(json.getString("email"));
            // Trả về json chấp nhận yêu cầu đăng nhập
            response = GenerateJson.ResponseLoginSuccess(
                    Integer.parseInt(information.get(0)),
                    information.get(1),
                    information.get(2),
                    information.get(3)
            );
        } else {
            // Ghi log thông báo phản hồi yêu cầu đăng nhập từ người dùng
            ServerLog.getInstance().writeLog(
                    "Server decline user ["
                            + json.getString("email")
                            + ",type:google] to logging in application.");

            // Trả về json từ chối yêu cầu đăng nhập.
            response = GenerateJson.ResponseLoginFailed();
        }
        SendMessage(response);
    }

    private void ServerResponseNormalRegister(JSONObject json) throws SQLException {
        boolean check = MySql.getInstance().CreateNewNormalUser(json);

        JSONObject response;
        if (check) {
            // Ghi log thông báo gửi phản hồi đăng ký thành công tới người dùng
            ServerLog.getInstance().writeLog("Server successfully create new user. " +
                    "Sent response to user");

            // Trả về json thông báo đăng ký thành công.
            response = GenerateJson.ResponseRegisterSuccess();
        } else {
            // Ghi log thông báo gửi phản hồi đăng ký thất bại tới người dùng
            ServerLog.getInstance().writeLog("Server failed to create new user. " +
                    "Sent response to user");

            // Trả về json thông báo đăng ký thất bại.
            response = GenerateJson.ResponseRegisterFailed();
        }

        SendMessage(response);
    }

    private void ServerResponseGoogleRegister(JSONObject json) throws SQLException {
        boolean check1 = MySql.getInstance().CreateNewGoogleUser(json);
        boolean check2 = MySql.getInstance().CreateUserLinkGoogle(json);

        JSONObject response;
        if (check1 && check2) {
            // Ghi log thông báo gửi phản hồi đăng ký thành công tới người dùng
            ServerLog.getInstance().writeLog("Server successfully create new user. " +
                    "Sent response to user");

            // Trả về json thông báo đăng ký thành công.
            response = GenerateJson.ResponseRegisterSuccess();
        } else {
            // Ghi log thông báo gửi phản hồi đăng ký thất bại tới người dùng
            ServerLog.getInstance().writeLog("Server failed to create new user. " +
                    "Sent response to user");

            // Trả về json thông báo đăng ký thất bại.
            response = GenerateJson.ResponseRegisterFailed();
        }
        SendMessage(response);
    }

    private void ServerAddNewRating(JSONObject json) {
        MySql.getInstance().AddNewRate(json);
    }



    private void ServerAddNewBorrowBookRequest(JSONObject json) {
        JsonFileHandler.getInstance().addJsonObject(json);
    }

    private void watchBorrowJsonFile() {
        try {
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        // Kiểm tra xem file log có thay đổi
                        if (event.context().toString().equals(
                                Paths.get(DATA.SERVER_BORROW_JSON_FILE).getFileName().toString())) {
                            // cật nhật giao diện
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - lastModifiedTime > 500) {
                                lastModifiedTime = currentTime;
                                Platform.runLater(this::SendBorrowFeedback);
                            }
                        }
                    }
                }
                key.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

    private void SendBorrowFeedback() {
        JSONArray jsonArray = JsonFileHandler.getInstance().ReadJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            JsonType status = JsonType.valueOf(jsonArray.getJSONObject(i).getString("status"));
            switch (status) {
                case BORROW_ACCEPTED -> SendMessage(jsonArray.getJSONObject(i));
                case BORROW_DECLINED, BORROW_RECALL -> {
                    SendMessage(jsonArray.getJSONObject(i));
                    JsonFileHandler.getInstance().removeJsonObject(jsonArray.getJSONObject(i));
                }
            }
        }
    }

    private void ServerChangeUserPassword(JSONObject json) {
        boolean check = MySql.getInstance().QueryChangePassword(
                json.getInt("user_id"), json.getString("new_password"));
        if (check) {
            SendMessage(GenerateJson.ResponseChangePasswordSuccess(json.getInt("user_id")));
        } else {
            SendMessage(GenerateJson.ResponseChangePasswordFailed(json.getInt("user_id")));
        }
    }
}