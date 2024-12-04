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

    public static JSONObject ResponseAcceptBorrowBook(int user_Id, String book_id, String url) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.BORROW_BOOK);
        json.put("user_id", user_Id);
        json.put("book_id", book_id);
        json.put("status", JsonType.BORROW_ACCEPTED);
        json.put("url", url);
        return json;
    }

    public static JSONObject ResponseDeclineBorrowBook(int user_Id, String book_id) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.BORROW_BOOK);
        json.put("user_id", user_Id);
        json.put("book_id", book_id);
        json.put("status", JsonType.BORROW_DECLINED);
        return json;
    }

    public static JSONObject ResponseBookRecall(int user_Id, String book_id) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.BORROW_BOOK);
        json.put("user_id", user_Id);
        json.put("book_id", book_id);
        json.put("status", JsonType.BORROW_RECALL);
        return json;
    }

    public static JSONObject ResponseChangePasswordSuccess(int user_id) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.CHANGE_PASSWORD_RESPONSE);
        json.put("user_id", user_id);
        json.put("message", Message.SUCCESS);
        return json;
    }

    public static JSONObject ResponseChangePasswordFailed(int user_id) {
        JSONObject json = new JSONObject();
        json.put("type", JsonType.CHANGE_PASSWORD_RESPONSE);
        json.put("user_id", user_id);
        json.put("message", Message.FAILED);
        return json;
    }
}
