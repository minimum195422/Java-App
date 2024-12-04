package project.libraryclient.Client;

import org.json.JSONObject;
import project.libraryclient.Consts.JsonType;
import project.libraryclient.Consts.Message;
import project.libraryclient.Consts.UserStatus;
import project.libraryclient.Models.JsonFileHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static Client client;
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private UserStatus status;


    private int userId;
    private String userMail;
    private String userName;

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
                    // xử lý response từ server gửi về
                    handleServerResponse(response);
                    // System.out.println(response);
                }
            } catch (IOException e) {
                System.err.println("Error while reading server response: " + e.getMessage());
            }
        }).start();
    }

    private void handleServerResponse(String response) {
        JSONObject json = new JSONObject(response);
//        System.out.println(json);
        JsonType type = JsonType.valueOf(json.getString("type"));

        switch (type) {
            case LOGIN_RESPONSE -> {
                if (Message.valueOf(json.getString("message")) == Message.SUCCESS) {
                    synchronized (this) {
                        this.userId = json.optInt("id", -1);
                        this.userName = json.optString("first_name", "") + " " + json.optString("last_name", "");
                        this.userMail = json.optString("email", null);
                        notifyListeners();
                        notify();
                    }
                    SetStatus(UserStatus.LOGGED_IN);
                }
                if (Message.valueOf(json.getString("message")) == Message.FAILED) {
                    SetStatus(UserStatus.LOGIN_FAILED);
                }
            }
            case REGISTER_RESPONSE -> {
                if (Message.valueOf(json.getString("message")) == Message.SUCCESS) {
                    SetStatus(UserStatus.REGISTER_SUCCESS);
                }
                if (Message.valueOf(json.getString("message")) == Message.FAILED) {
                    SetStatus(UserStatus.REGISTER_FAILED);
                }
            }
            case BORROW_BOOK -> {
                if (json.getInt("user_id") == userId) {
                    if (JsonType.valueOf(json.getString("status")) == JsonType.BORROW_ACCEPTED) {
                        JsonFileHandler.getInstance().replaceJsonObject(json);
                    }
                    if (JsonType.valueOf(json.getString("status")) == JsonType.BORROW_DECLINED) {
                        JsonFileHandler.getInstance().removeJsonObject(
                                json.getInt("user_id"), json.getString("book_id")
                        );
                    }
                    if (JsonType.valueOf(json.getString("status")) == JsonType.BORROW_RECALL) {
                        JsonFileHandler.getInstance().removeJsonObject(
                                json.getInt("user_id"), json.getString("book_id")
                        );
                    }
                }
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

    public int getUserId() {
        return userId;
    }

    public synchronized String getUserMail() {
        return userMail;
    }

    public synchronized String getUserName() {
        return userName;
    }

    // listener
    private List<ClientListener> listeners = new ArrayList<>();

    public void addListener(ClientListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ClientListener listener : listeners) {
            listener.onUserInfoUpdated(userId, userName, userMail);
        }
    }
}