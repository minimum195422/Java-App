package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.libraryserver.Book.Book;
import project.libraryserver.Database.MySql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddNewBookController implements Initializable {

    @FXML
    public VBox ListLog;

    private ArrayList<Book> getBookList(String name) throws IOException {
        ArrayList<Book> bookList = new ArrayList<>();
        String url = String.format("https://www.googleapis.com/books/v1/volumes?q=%s", name);
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response code: " + responseCode);

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();
            JSONObject myResponse = new JSONObject(response.toString());
            JSONArray bookListInJSON = myResponse.getJSONArray("items");
//            System.out.println(myResponse);
//            System.out.println(bookListInJSON);
            for (Object item: bookListInJSON) {
                JSONObject bookInJSON = (JSONObject) item;
                String title = bookInJSON.getJSONObject("volumeInfo").getString("title");
                ArrayList<String> authors = new ArrayList<>();
                try {
                    JSONArray authorList = bookInJSON.getJSONObject("volumeInfo").getJSONArray("authors");
                    for (Object author: authorList) {
                        authors.add((String) author);
                    }
                } catch (JSONException _) {

                }
                String ISBN = "Unknown";
                try {
                    ISBN = bookInJSON.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
                } catch (JSONException _) {

                }
                String sellable = bookInJSON.getJSONObject("saleInfo").getString("saleability");
                double price;
                if (Objects.equals(sellable, "NOT_FOR_SALE") || Objects.equals(sellable, "FREE")) {
                    price = 0;
                } else {
                    price = bookInJSON.getJSONObject("saleInfo").getJSONObject("listPrice").getDouble("amount");
                }
                String publishedDate = "Unknown";
                try {
                    publishedDate = bookInJSON.getJSONObject("volumeInfo").getString("publishedDate");
                } catch (JSONException _) {

                }
                ArrayList<String> categories = new ArrayList<>();
                try {
                    JSONArray categoryList = bookInJSON.getJSONObject("volumeInfo").getJSONArray("categories");
                    for (Object category: categoryList) {
                        categories.add((String) category);
                    }
                } catch (JSONException _) {

                }
                String imagePreview = "";
                try {
                    imagePreview = bookInJSON.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                } catch (JSONException _) {

                }
                String description = "No description";
                try {
                    description = bookInJSON.getJSONObject("volumeInfo").getString("description");
                } catch (JSONException _) {

                }
                Book book = new Book(title, authors, ISBN, price, publishedDate, categories, imagePreview, description);
                bookList.add(book);
            }
        }
        con.disconnect();
        return bookList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<Book> bookList = getBookList("novel");
            for (Book book: bookList) {
                MySql.addNewBook(book);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
