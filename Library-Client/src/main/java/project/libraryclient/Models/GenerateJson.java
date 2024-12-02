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

    public static JSONObject CreateNormalRegisterRequest(
            String firstName, String lastName, String email, String password) {
        JSONObject json = new JSONObject();
        json.put("type",JsonType.NORMAL_REGISTER);
        json.put("first_name", firstName);
        json.put("last_name", lastName);
        json.put("email", email);
        json.put("password", password);
        return json;
    }

    public static JSONObject CreateGoogleRegisterRequest(
            String google_id, String given_name, String family_name, 
            String email, String picture_link) {
        JSONObject json = new JSONObject();
        json.put("type",JsonType.GOOGLE_REGISTER);
        json.put("id", google_id);
        json.put("given_name", given_name);
        json.put("family_name", family_name);
        json.put("email", email);
        json.put("picture_link", picture_link);
        return json;
    }
    
    public static JSONObject CreateRatingRequest(String user_id, String book_id, int rate) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.RATING_BOOK);
        json.put("user_id", Integer.parseInt(user_id));
        json.put("book_id", book_id);
        json.put("rate", rate);
        return json;
    }
}
