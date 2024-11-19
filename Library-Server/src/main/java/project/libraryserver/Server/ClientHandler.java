package project.libraryserver.Server;

import org.json.JSONObject;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Consts.Message;
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
//                line = line.trim(); // Xóa ký tự thừa \n \r
                if (line.isEmpty()) {
                    continue; // Bỏ qua dòng trống
                }
                JSONObject json = null;

                try {
                    json = new JSONObject(line);
                    System.out.println("Server: valid json received " + json);

//                    out.println("Valid JSON received"); // Phản hồi client
//                    out.flush();
                } catch (Exception e) {
                    System.out.println("Invalid JSON input: " + line);
                }

                if (json != null) {
                    HandlerUserRequest(json);
                } else {
                    System.out.println("Failed to load json or wrong json format");
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
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

    private void HandlerUserRequest(JSONObject json) throws SQLException {
        JsonType type = JsonType.valueOf(json.getString("type"));

        switch (type) {
            case NORMAL_LOGIN -> ServerResponseNormalLogin(json);
            case GOOGLE_LOGIN -> System.out.println("google login");
            default -> throw new IllegalArgumentException("Unsupported JSON type");
        }
    }

    private void ServerResponseNormalLogin(JSONObject json) throws SQLException {
        boolean check = MySql.getInstance().QueryCheckNormalLogin(
                json.getString("email"), json.getString("password"));
        JSONObject response;
        if (check) {
            response = GenerateJson.CreateResponseLoginRequest(
                    JsonType.LOGIN_RESPONSE, Message.SUCCESS);
        } else {
            response = GenerateJson.CreateResponseLoginRequest(
                    JsonType.LOGIN_RESPONSE, Message.FAILED);
        }
        SendMessage(response);
    }
}
