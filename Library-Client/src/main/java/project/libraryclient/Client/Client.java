package project.libraryclient.Client;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final Scanner sc;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.sc = new Scanner(System.in);
    }

    public void sendMessage(String message) {
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
        String line = null;
        while (!"exit".equalsIgnoreCase(line)) {
            try {
                line = sc.nextLine();
                sendMessage(line);

                String tmp = in.readLine();
                System.out.println(tmp);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

//    // driver code
//    public static void main(String[] args)
//    {
//            while (!"exit".equalsIgnoreCase(line)) {
//
//                // reading from user
//                line = sc.nextLine();
//
//                // // send
//                out.println(line);
//                out.flush();
//
//                String tmp = in.readLine();
//                JSONObject jsonObject = new JSONObject(tmp);
//
//                // // receive
//                System.out.println("Server replied " + jsonObject.get("name"));
//
////                System.out.println(jsonObject.get("name"));
//            }
//    }
}