package project.libraryclient.Client;

import org.json.JSONObject;
import project.libraryclient.Consts.JsonType;
import project.libraryclient.Consts.Message;
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

    public void SendMessage(JSONObject message) {
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
                    System.out.println(response);
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
            case LOGIN_RESPONSE -> {
                if (Message.valueOf(json.getString("message")) == Message.SUCCESS) {
                    SetStatus(UserStatus.LOGGED_IN);
                }
                if (Message.valueOf(json.getString("message")) == Message.FAILED) {
                    SetStatus(UserStatus.LOGIN_FAILED);
                }
            }
            case GOOGLE_LOGIN -> {
                System.out.println("none");
            }
            case NORMAL_REGISTER -> {
                SetStatus(UserStatus.REGISTERED_COMPLETED);
            }
            default -> throw new RuntimeException("Invalid json file");
        }

    }

    public synchronized void ResetStatus() {
        this.status = UserStatus.START;
    }

    public synchronized void SetStatus(UserStatus s) {
        this.status = s;
        notify(); // Thông báo cho luồng đang chờ
    }

    public synchronized UserStatus WaitForStatusUpdate() throws InterruptedException {
        while (status == UserStatus.START) { // Kiểm tra trạng thái hiện tại
            wait(); // Chờ đến khi có thông báo
        }
        return status;
    }

}