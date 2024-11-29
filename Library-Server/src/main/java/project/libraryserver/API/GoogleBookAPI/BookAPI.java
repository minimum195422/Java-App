package project.libraryserver.API.GoogleBookAPI;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.libraryserver.Book.Book;
import project.libraryserver.Consts.SearchType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BookAPI {

    public static ArrayList<Book> SearchBook(String query, ArrayList<SearchType> types) throws URISyntaxException, IOException {

        if (query.isEmpty()) return new ArrayList<>();

        JSONObject json;
        StringBuilder SearchByType = new StringBuilder();
        if (types.isEmpty()) {
            json = requestJson(query.trim().replaceAll("\\s+", "+"), "");
        } else {
            for (SearchType type : types) {
                SearchByType.append("+");
                switch (type) {
                    case INAUTHOR -> SearchByType.append("inauthor");
                    case INSUBJECT -> SearchByType.append("subject");
                    case INTITLE -> SearchByType.append("intitle");
                    case INISBN -> SearchByType.append("isbn");
                    case INPUBLISHER -> SearchByType.append("inpublisher");
                }
            }
            json = requestJson(query, SearchByType.toString());
        }


        return GetListBook(json);
    }

    private static JSONObject requestJson(String query, String types) throws URISyntaxException, IOException {
        URI uri = new URI(String.format("https://www.googleapis.com/books/v1/volumes?q=%s%s", query, types));
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Can not connect to google book api");
            return null;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            // System.out.println(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    private static ArrayList<Book> GetListBook(JSONObject json) {
        if (json == null) return new ArrayList<>();
        if (json.getInt("totalItems") == 0) {
            System.out.println("No result found");
            return new ArrayList<>();
        }

        ArrayList<Book> returnList = new ArrayList<>();

        JSONArray jsonArray = json.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); ++i) {
            returnList.add(JsonToBook(jsonArray.getJSONObject(i)));
        }

        return returnList;
    }

    private static Book JsonToBook(JSONObject json) {

        try {
            JSONObject volumeInfo = new JSONObject();
            volumeInfo = json.getJSONObject("volumeInfo");
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("can not find volumeInfo");
        }

        return new Book(
                getBookId(json),
                getBookTitle(json.getJSONObject("volumeInfo")),
                getBookAuthor(json.getJSONObject("volumeInfo")),
                getBookPublisher(json.getJSONObject("volumeInfo")),
                getBookPublishedDate(json.getJSONObject("volumeInfo")),
                getBookDescription(json.getJSONObject("volumeInfo")),
                getBookCategories(json.getJSONObject("volumeInfo")),
                getBookISBN(json.getJSONObject("volumeInfo")).get(0),
                getBookISBN(json.getJSONObject("volumeInfo")).get(1),
                getBookCover(json.getJSONObject("volumeInfo")),
                getBookWebReadLink(json.getJSONObject("accessInfo"))
        );
    }



    private static String JsonArrayToString(JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); ++i) {
            stringBuilder.append(jsonArray.get(i).toString());
            if (i < jsonArray.length() - 1) stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    private static String getBookId(JSONObject json) {
        if (json.has("id")) {
            return json.getString("id");
        }
        return "no id";
    }

    private static String getBookTitle(JSONObject volumeInfo) {
        if (volumeInfo.has("title")) {
            return volumeInfo.getString("title");
        }
        return "Can't found title";
    }

    private static ArrayList<String> getBookAuthor(JSONObject volumeInfo) {
        try {
            if (volumeInfo.has("authors")) {
                String authorList = JsonArrayToString(volumeInfo.getJSONArray("authors"));
                return new ArrayList<>(Arrays.asList(authorList.split(",")));
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load author json array");
        }
        return new ArrayList<>();
    }

    public static String getBookPublisher(JSONObject volumeInfo) {
        try {
            if (volumeInfo.has("publisher")) {
                return volumeInfo.get("publisher").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load publisher");
        }
        return "Can't found publisher";
    }

    public static String getBookPublishedDate(JSONObject volumeInfo) {
        try {
            if (volumeInfo.has("publishedDate")) {
                return volumeInfo.get("publishedDate").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load publish date");
        }
        return "Can't found time release";
    }

    public static String getBookDescription(JSONObject volumeInfo) {
        try {
            if (volumeInfo.has("description")) {
                return volumeInfo.get("description").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load description");
        }
        return "Can't found description";
    }

    public static ArrayList<String> getBookCategories(JSONObject volumeInfo) {
        try {
            if (volumeInfo.has("categories")) {
                String categoryList = JsonArrayToString(volumeInfo.getJSONArray("categories"));
                return new ArrayList<>(Arrays.asList(categoryList.split(",")));
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load categories");
        }
        return new ArrayList<>();
    }

    public static ArrayList<String> getBookISBN(JSONObject volumeInfo) {
        String isbn13 = "Can't found isbn";
        String isbn10 = "Can't found isbn";
        try {
            if (volumeInfo.has("industryIdentifiers")) {
                JSONArray temp = volumeInfo.getJSONArray("industryIdentifiers");
                for (int i = 0; i < temp.length(); ++i) {
                    JSONObject isbn = temp.getJSONObject(i);
                    switch (isbn.getString("type")) {
                        case "ISBN_13" -> {
                            isbn13 = isbn.getString("identifier");
                        }
                        case "ISBN_10" -> {
                            isbn10 = isbn.getString("identifier");
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load isbn");
        }
        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.add(isbn13);
        returnArray.add(isbn10);
        return returnArray;
    }

    public static Image getBookCover(JSONObject volumeInfo) {
        Image tempImage = null;

        try {
            if (volumeInfo.has("imageLinks"))
                if (volumeInfo.getJSONObject("imageLinks").has("thumbnail")) {
                    try {
                        URI uri = new URI(
                                volumeInfo.getJSONObject("imageLinks").get("thumbnail").toString()
                        );
                        URL url = uri.toURL();
                        tempImage = new Image(url.toString());

                    } catch (URISyntaxException | MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    return tempImage;
                }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static String getBookWebReadLink(JSONObject accessInfo) {
        try {
            if (accessInfo.has("webReaderLink")) {
                return accessInfo.get("webReaderLink").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load description");
        }
        return "Can't found reader link";
    }
}
