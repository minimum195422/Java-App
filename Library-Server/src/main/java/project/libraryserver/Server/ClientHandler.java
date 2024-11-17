package project.libraryserver.Server;

import org.json.JSONObject;
import project.libraryserver.Consts.JsonType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim(); // Xóa ký tự thừa \n \r
                if (line.isEmpty()) {
                    continue; // Bỏ qua dòng trống
                }

                try {
                    JSONObject json = new JSONObject(line);
                    System.out.println("Valid JSON received: " + json);
                    JsonType type = JsonType.valueOf(json.getString("type"));
                    switch (type) {
                        case NORMAL_LOGIN -> {
                            System.out.println("normal login");
                            checkLoginNormal(json);
                        }
                        case GOOGLE_LOGIN -> {
                            System.out.println("google login");
                            checkLoginGoogle(json);
                        }
                        default -> throw new IllegalArgumentException("Unsupported JSON type");
                    }
                    out.println("Valid JSON received"); // Phản hồi client
                } catch (Exception e) {
                    System.out.println("Invalid JSON input: " + line);
                    out.println("Invalid JSON format"); // Phản hồi client
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
        finally {
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


    public void checkLoginNormal(JSONObject json) {
        System.out.println("check user login information");
        System.out.println(json.getString("type"));
        System.out.println(json.getString("email"));
        System.out.println(json.getString("password"));
    }

    public void checkLoginGoogle(JSONObject json) {
        System.out.println("check user login information");
    }
}
