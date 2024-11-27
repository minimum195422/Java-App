package project.libraryserver.Book;

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
    private String imagePreview;
    private String webReaderLink;


    private double price;

    public Book() {
        id = "";
        title = "";
        author = new ArrayList<>();
        publisher = "";
        publishedDate = "";
        description = "";
        ISBN_13 = "Can't found isbn";
        ISBN_10 = "Can't found isbn";
        imagePreview = "";
        webReaderLink = "";
        price = 0;
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

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getImagePreview() {
        return imagePreview;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getPrice() {
        return price;
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
                "price=<" + price + ">\n" +
                '}';
    }
}
