package project.libraryclient.Models;

import project.libraryclient.Consts.DATA;

import java.io.*;
import java.util.ArrayList;

public class NotificationHandler {
    private static NotificationHandler instance;
    private final String logFilePath;

    private NotificationHandler() {
        this.logFilePath = DATA.NOTIFICATION_FILE;
    }

    public static NotificationHandler getInstance() {
        if (instance == null) {
            instance = new NotificationHandler();
        }
        return instance;
    }

    public synchronized void writeFile(String message) {
        File logFile = new File(logFilePath);

        try {
            // Ghi dữ liệu vào file
            // Với chế độ ghi là append (không ghi đè)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public synchronized ArrayList<String> readFile() {
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
