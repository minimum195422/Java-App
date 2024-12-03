package project.libraryserver.Models;

import org.json.JSONObject;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Consts.Message;


public class GenerateJson {

    public static JSONObject ResponseLoginSuccess(
            String id, String first_name, String last_name, String email) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.LOGIN_RESPONSE);
        json.put("message", Message.SUCCESS);
        json.put("id", id);
        json.put("email", email);
        json.put("first_name", first_name);
        json.put("last_name", last_name);
        return json;
    }

    public static JSONObject ResponseLoginFailed() {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.LOGIN_RESPONSE);
        json.put("message", Message.FAILED);
        return json;
    }

    public static JSONObject ResponseRegisterSuccess() {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.REGISTER_RESPONSE);
        json.put("message", Message.SUCCESS);
        return json;
    }

    public static JSONObject ResponseRegisterFailed() {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.REGISTER_RESPONSE);
        json.put("message", Message.FAILED);
        return json;
    }

}
