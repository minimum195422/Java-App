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
        boolean check = MySql.getInstance().QueryCheckNormalLogin(
                json.getString("email"), json.getString("password"));
        JSONObject response;
        if (check) {
            response = GenerateJson.ResponseLoginSuccess();
        } else {
            response = GenerateJson.ResponseLoginFailed();
        }
        SendMessage(response);
    }

    private void ServerResponseGoogleLogin(JSONObject json) throws SQLException {
        boolean check = MySql.getInstance().QueryCheckGoogleLogin(
                json.getString("id"), json.getString("email"));
        JSONObject response;
        if (check) {
            response = GenerateJson.ResponseLoginSuccess();
        } else {
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
            response = GenerateJson.ResponseRegisterSuccess();
        } else {
            response = GenerateJson.ResponseRegisterFailed();
        }

        SendMessage(response);
    }

    private void ServerResponseGoogleRegister(JSONObject json) {
        JSONObject response;
        response = GenerateJson.ResponseRegisterSuccess();
        SendMessage(response);
    }
}
