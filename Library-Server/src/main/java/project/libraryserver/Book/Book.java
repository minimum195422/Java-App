package project.libraryserver.Book;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Controllers.Card.BookCard_600_200;

import java.io.IOException;
import java.util.ArrayList;

public class Book {
    private String id;
    private String title;
    private ArrayList<String> author;
    private String publisher;
    private String publishedDate;
    private String description;
    private ArrayList<String> categories;
    private String ISBN_13;
    private String ISBN_10;
    private Image imagePreview;
    private String webReaderLink;
    private AnchorPane DisplayCard;
    private BookCard_600_200 Controller;

//    private int price;


    public Book(String id, String title, ArrayList<String> author,
                String publisher, String publishedDate, String description,
                ArrayList<String> categories, String ISBN_13, String ISBN_10,
                Image imagePreview, String webReaderLink) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.categories = categories;
        this.ISBN_13 = ISBN_13;
        this.ISBN_10 = ISBN_10;
        this.imagePreview = imagePreview;
        this.webReaderLink = webReaderLink;
        LoadDisplayCard();
    }

    private void LoadDisplayCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BOOK_DISPLAY_CARD_LINK));
            DisplayCard = loader.load();
            Controller = loader.getController();
            Controller.setInfor(imagePreview, title, publisher, ISBN_13, ISBN_10, publishedDate, author.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setISBN_13(String ISBN_13) {
        this.ISBN_13 = ISBN_13;
    }

    public void setISBN_10(String ISBN_10) {
        this.ISBN_10 = ISBN_10;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setImagePreview(Image imagePreview) {
        this.imagePreview = imagePreview;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public void setDisplayCard(AnchorPane displayCard) {
        DisplayCard = displayCard;
    }

    public void setController(BookCard_600_200 controller) {
        Controller = controller;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthor() {
        return author;
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

    public AnchorPane getDisplayCard() {
        return DisplayCard;
    }

    public BookCard_600_200 getController() {
        return Controller;
    }

    public String toString() {
        return "Book{" +
                "id=<" + id + ">\n" +
                "title=<" + title + ">\n" +
                "author=<" + author + ">\n" +
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
