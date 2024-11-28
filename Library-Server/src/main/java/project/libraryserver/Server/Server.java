package project.libraryserver.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Server instance;   // Biến singleton
    private ServerSocket server;      // ServerSocket để lắng nghe kết nối từ client
    private boolean running;          // Trạng thái server


    private Server() {
        this.running = true;
    }

    // Phương thức singleton để lấy instance của Server
    public static synchronized Server getInstance() throws IOException {
        if (instance == null) {
            instance = new Server();
            instance.startServer();
        }
        return instance;
    }

    // Phương thức để bắt đầu server và xử lý kết nối
    private void startServer() {
        try {
            // Server lắng nghe trên cổng 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            ServerLog.getInstance().writeLog("Server stating on port 1234");

            // Vòng lặp vô hạn để chờ và chấp nhận kết nối client
            while (running) {
                // Socket nhận yêu cầu kết nối từ client
                Socket client = server.accept();

                // Hiển thị thông báo client kết nối
                // System.out.println("New client connected: "
                //        + client.getInetAddress().getHostAddress());

                // Ghi logfile thông báo client đã kết nối
                ServerLog.getInstance().writeLog(
                        "Client "
                        + client.getInetAddress().getHostAddress()
                        + " connected.");

                // Tạo đối tượng ClientHandler để xử lý kết nối client này
                ClientHandler clientHandler = new ClientHandler(client);

                // Tạo một thread mới để xử lý client
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            ServerLog.getInstance().writeLog("Server stop.");
            stopServer();  // Dừng server khi có lỗi hoặc khi server dừng
        }
    }


    public void stopServer() {
        this.running = false;
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean isRunning() {
        return running;
    }
}
