package project.libraryclient.API.GoogleOauthAPI;

import org.json.JSONObject;
import project.libraryclient.Consts.DATA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.stream.Collectors;


public abstract class OAuthAuthenticator {

    private JSONObject UserInformation;
    private String AUTHORIZATION_CODE;
    private String ACCESS_TOKEN;


    OAuthAuthenticator () {
        this.AUTHORIZATION_CODE = "";
        this.ACCESS_TOKEN = "";
    }

    public JSONObject GetUserInformation() {
        return UserInformation;
    }

    public void SetUserInformation(JSONObject json) {
        this.UserInformation = json;
    }

    public abstract void start();

    public void exchangeCodeForToken() {
        try {
            // Tạo URL yêu cầu token
            String tokenUrl = "https://oauth2.googleapis.com/token";

            // Cấu trúc các tham số yêu cầu POST
            String params = "code=" + AUTHORIZATION_CODE +
                    "&client_id=" + DATA.GetGoogleClientId() +
                    "&client_secret=" + DATA.GetGoogleClientSecret()+
                    "&redirect_uri=" + DATA.GetGoogleRedirectLink() +
                    "&grant_type=authorization_code";

            // Gửi yêu cầu POST để lấy access token
            HttpURLConnection connection = (HttpURLConnection) new URL(tokenUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(params.getBytes("UTF-8"));

            // Xử lý phản hồi từ Google
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Code = 200, phản hổi thành công, đọc token được trả về
                String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
                // System.out.println("Response: " + response);

                // Nhận access token được trả về và đổi thành json
                JSONObject jsonResponse = new JSONObject(response);
                ACCESS_TOKEN = jsonResponse.getString("access_token");
                // System.out.println("Access Token: " + ACCESS_TOKEN);
            } else {
                // Thông báo lỗi
                System.out.println("Error getting access token! Response code: " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorMessage = in.lines().collect(Collectors.joining("\n"));
                System.out.println("Error response: " + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public String extractAuthorizationCode(String url) {
        try {
            URI uri = new URI(url);
            String query = uri.getQuery();
            if (query != null && query.contains("code=")) {
                return query.split("code=")[1];
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public JSONObject fetchUserInfo() {
        String response = "";
        try {
            // URL API để lấy thông tin người dùng
            String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(userInfoUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + getACCESS_TOKEN());

            // Kiểm tra mã phản hồi
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Đọc phản hồi JSON từ API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                response = in.lines().collect(Collectors.joining("\n"));
                in.close();
            } else {
                System.out.println("Error fetching user info. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println(response);
        return new JSONObject(response);
    }

    public void SetAUTHORIZATION_CODE(String AUTHORIZATION_CODE) {
        this.AUTHORIZATION_CODE = AUTHORIZATION_CODE;
    }

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }
}