import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class client {

    // driver code
    public static void main(String[] args)
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                // reading from user
                line = sc.nextLine();

                // // send
                out.println(line);
                out.flush();

                String tmp = in.readLine();
                // // receive
                System.out.println("Server replied "
                        + tmp);
                JSONObject jsonObject = new JSONObject(tmp);
//                System.out.println(jsonObject.get("name"));
            }

            // closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}