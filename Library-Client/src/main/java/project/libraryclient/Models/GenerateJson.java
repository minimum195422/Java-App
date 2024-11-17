package project.libraryclient.Models;

import org.json.JSONObject;
import project.libraryclient.Consts.JsonType;

public class GenerateJson {

    public static JSONObject createJsonByType(JsonType type) {
        JSONObject json = new JSONObject();
        switch (type) {
            case NORMAL_LOGIN -> {
                json.put("Type", JsonType.NORMAL_LOGIN);
                json.put("IdNormal", "userId");
                json.put("Password", "userPassword");
            }
            case GOOGLE_LOGIN -> {
                json.put("Type", JsonType.GOOGLE_LOGIN);
                json.put("IdGoogle", "userId");
                json.put("Password", "userPassword");
            }
            default -> throw new IllegalArgumentException("Unsupported JSON type");
        }
        return json;
    }

    public static JSONObject createNormalLoginRequest(String email, String password) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.NORMAL_LOGIN);
        json.put("email", email);
        json.put("password", password);
        return json;
    }
}
