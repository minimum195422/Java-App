package project.libraryserver.Book;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Controllers.Card.BookCard_1020_50;
import project.libraryserver.Controllers.Card.BookCard_600_200;

import java.io.IOException;
import java.util.ArrayList;

public class Book {
    private final String id;
    private final String title;
    private final ArrayList<String> authors;
    private final String publisher;
    private final String publishedDate;
    private final String description;
    private final ArrayList<String> categories;
    private final String ISBN_13;
    private final String ISBN_10;
    private final Image imagePreview;
    private final String webReaderLink;

    // for display google search book
    private AnchorPane DisplayCardForGoogleSearch;
    private BookCard_600_200 Book_600_200_Controller;

    // for display manage book
    private AnchorPane DisplayCardForManage;
    private BookCard_1020_50 Book_1020_50_Controller;


    public Book() {
        id = null;
        title = null;
        authors = null;
        publisher = null;
        publishedDate = null;
        description = null;
        categories = null;
        ISBN_10 = null;
        ISBN_13 = null;
        imagePreview = null;
        webReaderLink = null;
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
        LoadDisplayCard();
    }

    private void LoadDisplayCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BOOK_DISPLAY_CARD_600_200_LINK));
            DisplayCardForGoogleSearch = loader.load();
            Book_600_200_Controller = loader.getController();
            Book_600_200_Controller.setInfor(
                    imagePreview,
                    title,
                    publisher,
                    String.join(", ", categories),
                    publishedDate,
                    String.join(", ", authors));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BOOK_DISPLAY_CARD_1020_50_LINK));
            DisplayCardForManage = loader.load();
            Book_1020_50_Controller = loader.getController();
            Book_1020_50_Controller.setInfor(
                    id,
                    title,
                    String.join(", ", authors),
                    "0.0",
                    "100"
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
