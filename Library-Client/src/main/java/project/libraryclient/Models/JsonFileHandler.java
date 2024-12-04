package project.libraryclient.Models;

import org.json.JSONArray;
import org.json.JSONObject;
import project.libraryclient.Consts.DATA;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JsonFileHandler {
    private static JsonFileHandler instance;
    private JsonFileHandler() {}

    public static synchronized JsonFileHandler getInstance() {
        if (instance == null) {
            instance = new JsonFileHandler();
        }
        return instance;
    }

    public synchronized JSONArray readJsonArray() {
        Path path = Paths.get(DATA.CLIENT_BORROW_JSON_FILE);

        if (!Files.exists(path)) {
            return new JSONArray();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            if (jsonContent.isEmpty()) {
                return new JSONArray();
            }

            return new JSONArray(jsonContent.toString());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return new JSONArray();
        }
    }

    public synchronized void writeJsonArray(JSONArray jsonArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA.CLIENT_BORROW_JSON_FILE))) {
            writer.write(jsonArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public synchronized void addJsonObject(JSONObject jsonObject) {
        JSONArray jsonArray = readJsonArray();
        jsonArray.put(jsonObject);
        writeJsonArray(jsonArray);
    }

    public synchronized void removeJsonObject(int user_id, String book_id) {
        JSONArray jsonArray = readJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).getInt("user_id") == user_id
                && jsonArray.getJSONObject(i).getString("book_id").equals(book_id)) {
                jsonArray.remove(i);
            }
        }
        writeJsonArray(jsonArray);
    }

    public synchronized void removeJsonObject(JSONObject json) {
        JSONArray jsonArray = readJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).similar(json)) {
                jsonArray.remove(i);
            }
        }
        writeJsonArray(jsonArray);
    }

    public synchronized void replaceJsonObject(JSONObject newJson) {
        JSONArray jsonArray = readJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).getInt("user_id") == newJson.getInt("user_id")
                    && jsonArray.getJSONObject(i).getString("book_id").equals(newJson.getString("book_id"))) {
                jsonArray.remove(i);
                break;
            }
        }
        jsonArray.put(newJson);
        writeJsonArray(jsonArray);
    }

    public synchronized JSONObject getJsonBy(int userId, String bookId) {
        JSONArray jsonArray = readJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).getInt("user_id") == userId
                    && jsonArray.getJSONObject(i).getString("book_id").equals(bookId)) {
                return jsonArray.getJSONObject(i);
            }
        }
        return null;
    }

    public synchronized boolean IsDuplicateJsonObject(JSONObject json) {
        JSONArray jsonArray = readJsonArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).getString("book_id")
                    .equals(json.getString("book_id"))) {
                return true;
            }
        }
        return false;
    }
}
