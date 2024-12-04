package project.libraryserver.Book;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Controllers.Card.BookCard_1020_50;
import project.libraryserver.Controllers.Card.BookCard_600_200;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("FieldMayBeFinal")
public class Book {
    private String id;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private ArrayList<String> categories;
    private String ISBN_13;
    private String ISBN_10;
    private Image imagePreview;
    private String webReaderLink;
    private double rating;
    private int borrowed_time;

    // for display google search book
    private AnchorPane DisplayCardForGoogleSearch;
    private BookCard_600_200 Book_600_200_Controller;

    // for display manage book
    private AnchorPane DisplayCardForManage;
    private BookCard_1020_50 Book_1020_50_Controller;


    public Book(String id, Image image_preview, String title,
                String publisher, ArrayList<String> categories,
                String published_date, ArrayList<String> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = published_date;
        this.categories = categories;
        this.imagePreview = image_preview;
        LoadDisplayCardForGoogleSearch();
    }

    public Book(String id, String title, ArrayList<String> authors,
                double rating, int borrowed_time) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.borrowed_time = borrowed_time;
        LoadDisplayCardForManage();
    }

    public Book(String id, String title, ArrayList<String> authors,
                String publisher, String published_date, String description,
                ArrayList<String> categories, String ISBN_13, String ISBN_10,
                Image image_preview, String web_reader_link) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = published_date;
        this.description = description;
        this.categories = categories;
        this.ISBN_13 = ISBN_13;
        this.ISBN_10 = ISBN_10;
        this.imagePreview = image_preview;
        this.webReaderLink = web_reader_link;
        LoadDisplayCardForGoogleSearch();
        LoadDisplayCardForManage();
    }

    private void LoadDisplayCardForGoogleSearch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BOOK_DISPLAY_CARD_600_200_LINK));
            DisplayCardForGoogleSearch = loader.load();
            Book_600_200_Controller = loader.getController();
            Book_600_200_Controller.setInfo(
                    imagePreview,
                    title,
                    publisher,
                    String.join(", ", categories),
                    publishedDate,
                    String.join(", ", authors));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadDisplayCardForManage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BOOK_DISPLAY_CARD_1020_50_LINK));
            DisplayCardForManage = loader.load();
            Book_1020_50_Controller = loader.getController();
            Book_1020_50_Controller.setInfo(
                    id,
                    title,
                    String.join(", ", authors),
                    String.valueOf((double) Math.round(rating * 10) / 10),
                    String.valueOf(borrowed_time)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }


    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getISBN_13() {
        return ISBN_13;
    }

    public String getISBN_10() {
        return ISBN_10;
    }

    public Image getImagePreview() {
        return imagePreview;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public int getBorrowed_time() {
        return borrowed_time;
    }
    public AnchorPane GetDisplayCardForGoogleSearch() {
        return DisplayCardForGoogleSearch;
    }

    public AnchorPane GetDisplayCardForManage() {
        return DisplayCardForManage;
    }

    public BookCard_1020_50 GetBook_1020_50_Controller() {
        return Book_1020_50_Controller;
    }

    public BookCard_600_200 GetBook_600_200_Controller() {
        return Book_600_200_Controller;
    }

    public String toString() {
        return "Book{" +
                "id=<" + id + ">\n" +
                "title=<" + title + ">\n" +
                "author=<" + authors + ">\n" +
                "publisher=<" + publisher + ">\n" +
                "publishedDate=<" + publishedDate + ">\n" +
                "description=<" + description + ">\n" +
                "categories=<" + categories + ">\n" +
                "ISBN_13=<" + ISBN_13 + ">\n" +
                "ISBN_10=<" + ISBN_10 + ">\n" +
                "imagePreview=<" + imagePreview + ">\n" +
                "webReaderLink=<" + webReaderLink + ">\n" +
                '}';
    }
}
