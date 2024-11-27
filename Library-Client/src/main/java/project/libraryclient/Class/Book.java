package project.libraryclient.Class;

import java.util.ArrayList;

public class Book {
    private int bookId;
    private String title;
    private ArrayList<String> author;
    private String ISBN;
    private double price;
    private String publishedDate;
    private ArrayList<String> categories;
    private String imagePreview;
    private String description;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorInString() {
        StringBuilder authors = new StringBuilder();
        for (int i = 0; i < author.size(); i++) {
            authors.append(author.get(i));
            if (i != author.size() - 1) {
                authors.append(", ");
            }
        }
        return authors.toString();
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getImagePreview() {
        return imagePreview;
    }

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 300) {
            this.description = "No description";
        } else {
            this.description = description;
        }
    }

    public Book() {

    }

    public Book(int id, String title, ArrayList<String> authors, String imagePreview) {
        setBookId(id);
        setTitle(title);
        setAuthor(authors);
        setImagePreview(imagePreview);
    }

    public Book(String title, ArrayList<String> authors, String ISBN, double price, String publishedDate,
                ArrayList<String> categories, String imagePreview, String description) {
        setAuthor(authors);
        setISBN(ISBN);
        setPrice(price);
        setPublishedDate(publishedDate);
        setCategories(categories);
        setImagePreview(imagePreview);
        setDescription(description);
    }

    public Book(int id, String title, ArrayList<String> authors, String ISBN, double price, String publishedDate,
                ArrayList<String> categories, String imagePreview, String description) {
        setBookId(id);
        setAuthor(authors);
        setISBN(ISBN);
        setPrice(price);
        setPublishedDate(publishedDate);
        setCategories(categories);
        setImagePreview(imagePreview);
        setDescription(description);
    }
}
