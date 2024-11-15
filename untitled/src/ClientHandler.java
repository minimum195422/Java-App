import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    public void run()
    {
        serverReceiveMessage();
    }

    public void serverReceiveMessage() {
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

                // writing the received message from
                // client

                // làm try catch ở đây cho login
                // dùng jsonobject để gửi request
                if (line.equals("1")) {
//                    JSONObject jsonObject = new JSONObject().put("name", "alex");
//                    jsonObject.put("pass", "pass");
//                    out.println(jsonObject); // jsonobject change to default string
//                    out.flush();
//                    jsonObject.clear();
                    out.println("one");
                    out.flush();
                } else if (line.equals("2")) {
                    out.println("type 2");
                } else {
                    out.println("none");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }
}