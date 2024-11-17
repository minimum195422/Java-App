package project.libraryclient.Client;

import com.google.api.client.json.Json;
import org.json.JSONObject;
import project.libraryclient.Consts.JsonType;
import project.libraryclient.Consts.UserStatus;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Client client;
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private UserStatus status;

    private Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        status = UserStatus.START;

        start();
    }

    public static Client getInstance() throws IOException {
        if (client == null) {
            client = new Client("localhost", 1234);
        }
        return client;
    }

    public void sendMessage(JSONObject message) {
        out.println(message);
        out.flush();
    }


    public void close() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void start() {
        new Thread(() -> {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    // handle response from server when user send request
                    handleServerResponse(response);
                }
            } catch (IOException e) {
                System.err.println("Error while reading server response: " + e.getMessage());
            }
        }).start();
    }

    private void handleServerResponse(String response) {
        JSONObject json = new JSONObject(response);
        JsonType type = JsonType.valueOf(json.getString("type"));
        switch (type) {
            case LOGIN_SUCCESS -> {
                status = UserStatus.LOGGED_IN;
            }
            case LOGIN_FAILED -> {
                status = UserStatus.LOGGING_IN_FAILED;
            }
            default -> throw new RuntimeException("Invalid json file");
        }
    }

    public UserStatus GetUserStatus() {
        return status;
    }
}