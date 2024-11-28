package project.libraryserver.Server;

import project.libraryserver.Consts.DATA;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ServerLog {
    private static ServerLog instance;
    private final String logFilePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ServerLog() {
        this.logFilePath = DATA.SERVER_LOG_FILE;
    }

    public static ServerLog getInstance() {
        if (instance == null) {
            instance = new ServerLog();
        }
        return instance;
    }

    public synchronized void writeLog(String message) {
        File logFile = new File(logFilePath);
        boolean isNewFile = false;

        try {
            // Kiểm tra xem file có tồn tại hay không
            // Nếu chưa thì tạo file mới
            if (!logFile.exists()) {
                isNewFile = logFile.createNewFile();
            }

            // Ghi dữ liệu vào file
            // Với chế độ ghi là append (không ghi đè)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                if (isNewFile) {
                    writer.write("==== New Log File Created ====");
                    writer.newLine();
                }
                String timestamp = LocalDateTime.now().format(formatter);
                writer.write(timestamp + " - " + message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public synchronized ArrayList<String> readLog() {
        ArrayList<String> logLines = new ArrayList<>();
        File logFile = new File(logFilePath);

        if (!logFile.exists()) {
            System.out.println("Log file does not exist.");
            return logLines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return logLines;
    }

}

