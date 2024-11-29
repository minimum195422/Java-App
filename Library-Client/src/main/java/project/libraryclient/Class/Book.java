package project.libraryclient.Class;

import javafx.scene.image.Image;
import java.util.ArrayList;

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

    public Book(String id, String title, ArrayList<String> authors,
                String publisher, String published_date, String description,
                ArrayList<String> categories, String ISBN_13, String ISBN_10,
                javafx.scene.image.Image image_preview, String web_reader_link) {
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
    }

    public Book(String prevId, String prevTitle, ArrayList<String> prevAuthor, Image prevImage) {
        this.id = prevId;
        this.title = prevTitle;
        this.authors = prevAuthor;
        this.imagePreview = prevImage;
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
