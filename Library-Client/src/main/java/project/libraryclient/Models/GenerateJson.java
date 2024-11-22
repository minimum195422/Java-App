package project.libraryclient.Models;

import org.json.JSONObject;
import project.libraryclient.Consts.JsonType;

public class GenerateJson {

    public static JSONObject CreateNormalLoginRequest(String email, String password) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.NORMAL_LOGIN);
        json.put("email", email);
        json.put("password", password);
        return json;
    }

    public static JSONObject CreateGoogleLoginRequest(String google_id, String email) {
        JSONObject json = new JSONObject();
        json.put("type",JsonType.GOOGLE_LOGIN);
        json.put("id", google_id);
        json.put("email", email);
        return json;
    }

    public static JSONObject CreateNormalRegisterRequest(String firstName, String lastName, String email, String password) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.NORMAL_REGISTER);
        json.put("first_name", firstName);
        json.put("last_name", lastName);
        json.put("email", email);
        json.put("password", password);
        return json;
    }

}
