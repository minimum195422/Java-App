package project.libraryserver.Book;

public class Book {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String description;
    private String ISBN_13;
    private String ISBN_10;
    private String imagePreview;
    private String webReaderLink;


//    private int price;

    public Book() {
        id = "";
        title = "";
        author = "";
        publisher = "";
        publishedDate = "";
        description = "";
        ISBN_13 = "Can't found isbn";
        ISBN_10 = "Can't found isbn";
        imagePreview = "";
        webReaderLink = "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
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

    public void setImagePreview(String imagePreview) {
        this.imagePreview = imagePreview;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
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

    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", ISBN_13='" + ISBN_13 + '\'' +
                ", ISBN_10='" + ISBN_10 + '\'' +
                ", imagePreview='" + imagePreview + '\'' +
                ", webReaderLink='" + webReaderLink + '\'' +
                '}';
    }
}
