package project.libraryserver.API.GoogleBookAPI;

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
import java.util.List;
import java.util.Scanner;

public class BookAPI {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        ArrayList<SearchType> types = new ArrayList<>();
//        types.add(SearchType.INAUTHOR);
        List<Book> t = SearchBook(s, types);
        if (!t.isEmpty()) {
            for (Book book : t) {
                System.out.println(book.toString());
            }
        }
    }

    public static ArrayList<Book> SearchBook(String query, ArrayList<SearchType> types) throws URISyntaxException, IOException {

        if (query.isEmpty()) return new ArrayList<>();

        JSONObject json;
        StringBuilder SearchByType = new StringBuilder();
        if (types.isEmpty()) {
            json = requestJson(query, "");
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
            System.out.println(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    private static ArrayList<Book> GetListBook(JSONObject json) {
        if (json == null) return new ArrayList<>();

        ArrayList<Book> returnList = new ArrayList<>();

        JSONArray jsonArray = json.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); ++i) {
            returnList.add(JsonToBook(jsonArray.getJSONObject(i)));
        }

        return returnList;
    }

    private static Book JsonToBook(JSONObject json) {
        Book returnBook = new Book();

        // book id
        if (json.has("id")) {
            returnBook.setId(json.getString("id"));
        } else {
            returnBook.setId("null");
        }

        JSONObject volumeInfo = new JSONObject();
        try {
            volumeInfo = json.getJSONObject("volumeInfo");
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("can not find volumeInfo");
        }

        // book title
        if (volumeInfo.has("title")) {
            returnBook.setTitle(volumeInfo.getString("title"));
        } else {
            returnBook.setTitle("Can't found title");
        }

        // author
        try {
            if (volumeInfo.has("authors")) {
                returnBook.setAuthor(
                        JsonArrayToString(volumeInfo.getJSONArray("authors"))
                );
            } else {
                returnBook.setAuthor("Can't found any author");
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load author json array");
        }

        // Get publisher
        try {
            if (volumeInfo.has("publisher")) {
                returnBook.setPublisher(volumeInfo.get("publisher").toString());
            } else {
                returnBook.setPublisher("Can't found publisher");
            }

        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load publisher");
        }

        // Get publish date
        try {
            if (volumeInfo.has("publishedDate")) {
                returnBook.setPublisher(volumeInfo.get("publishedDate").toString());
            } else {
                returnBook.setPublishedDate("Can't found time release");
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load publish date");
        }

        // Get description
        try {
            if (json.getJSONObject("volumeInfo").has("description")) {
                returnBook.setDescription(volumeInfo.get("description").toString());
            } else {
                returnBook.setDescription("Can't found description");
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load description");
        }

        // get isbn-13 and isbn-10
        try {
            if (volumeInfo.has("industryIdentifiers")) {
                JSONArray temp = volumeInfo.getJSONArray("industryIdentifiers");
                for (int i = 0; i < temp.length(); ++i) {
                    JSONObject isbn = temp.getJSONObject(i);
                    switch (isbn.getString("type")) {
                        case "ISBN_13" -> {
                            returnBook.setISBN_13(isbn.getString("identifier"));
                        }
                        case "ISBN_10" -> {
                            returnBook.setISBN_10(isbn.getString("identifier"));
                        }
                        default -> {
                            returnBook.setISBN_13("Can't found isbn");
                            returnBook.setISBN_10("Can't found isbn");
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace(System.out);
            System.out.println("fail to load isbn");
        }


        return returnBook;
    }

    public static String JsonArrayToString(JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); ++i) {
            stringBuilder.append(jsonArray.get(i).toString());
            if (i < jsonArray.length() - 1) stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
