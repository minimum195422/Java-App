package project.libraryserver.Models;

import org.json.JSONObject;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Consts.Message;


public class GenerateJson {

    public static JSONObject CreateResponseLoginRequest(JsonType type, Message message) {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("message", message);
        return json;
    }
}
