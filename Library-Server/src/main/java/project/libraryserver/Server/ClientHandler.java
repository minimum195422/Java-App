package project.libraryserver.Server;

import org.json.JSONObject;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Database.MySql;
import project.libraryserver.Models.GenerateJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    // Constructor
    public ClientHandler(Socket socket) throws IOException {
        clientSocket = socket;

        // get the outputstream of client
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        // get the inputstream of client
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
        out.println(json);
        out.flush();
    }

    // bắt json request từ client
    private void HandlerUserRequest(JSONObject json) throws SQLException {
        JsonType type = JsonType.valueOf(json.getString("type"));

        switch (type) {
            case NORMAL_LOGIN -> ServerResponseNormalLogin(json);
            case GOOGLE_LOGIN -> ServerResponseGoogleLogin(json);
            case NORMAL_REGISTER -> ServerResponseNormalRegister(json);
            case GOOGLE_REGISTER -> ServerResponseGoogleRegister(json);
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
            response = GenerateJson.ResponseLoginSuccess();
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

            // Trả về json chấp nhận yêu cầu đăng nhập
            response = GenerateJson.ResponseLoginSuccess();
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
        boolean check = MySql.getInstance().CreateNewNormalUser(
                json.getString("first_name"), json.getString("last_name"),
                json.getString("email"), json.getString("password"));

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
        boolean check = MySql.getInstance().CreateNewGoogleUser(
                json.getString("id"),
                json.getString("given_name"),
                json.getString("family_name"),
                json.getString("email"),
                json.getString("picture_link")
        );

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
}
