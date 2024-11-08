package Frontend.Class;

import javafx.scene.image.ImageView;

import java.awt.*;

public class Book {
    private String imagePreview;
    private String title;
    private String author;
    private String publishedDate;
    private String ISBN_13;
    private int price;

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getISBN_13() {
        return ISBN_13;
    }

    public void setISBN_13(String ISBN_13) {
        this.ISBN_13 = ISBN_13;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String imagePreview, String title, String author, String publishedDate, String ISBN_13, int price) {
        this.imagePreview = imagePreview;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.ISBN_13 = ISBN_13;
        this.price = price;
    }

    public Book(Book obj) {
        this.imagePreview = obj.getImagePreview();
        this.title = obj.getTitle();
        this.author = obj.getAuthor();
        this.publishedDate = obj.getPublishedDate();
        this.ISBN_13 = obj.getISBN_13();
        this.price = obj.getPrice();
    }
}
