package project.libraryclient.Database;

import javafx.scene.image.Image;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MySql {
    private static MySql instance;
    private static Connection connection;
    private boolean accessable;


    public MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.GetJdbcUrl(),
                    DATA.GetJdbcUser(),
                    DATA.GetJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.accessable = false;
    }

    public static synchronized MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
    }

    public void SetAccessable(boolean b) {
        this.accessable = b;
    }

    public static ArrayList<String> getBookBySubstring(String str) throws SQLException {
        ArrayList<String> bookList = new ArrayList<>();
        String SQL = "SELECT title FROM books WHERE title LIKE '%" + str + "%'";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String name = rs.getString(1);
            bookList.add(name);
//            System.out.println(name);
        }
        return bookList;
    }

    public static ArrayList<Book> getBasicInfoOfBook(String name) throws SQLException {
        ArrayList<Book> bookList = new ArrayList<>();
        Book book;
        String SQL = "SELECT books.book_id as id, title, group_concat(authors.name) as author, image_preview "
                + "FROM books "
                + "JOIN book_authors ON books.book_id = book_authors.book_id "
                + "JOIN authors ON authors.author_id = book_authors.author_id "
                + "WHERE title = ? "
                + "GROUP BY books.book_id";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        Image img;
        while (rs.next()) {
            // title, author, image
            String id = rs.getString(1);
            String title = rs.getString(2);
            String[] authorListInString = rs.getString(3).split(",");
            String image = rs.getString(4);

            ArrayList<String> authors = new ArrayList<>(Arrays.asList(authorListInString));
            img = new Image(image);
            book = new Book(id, title, authors, img);
            bookList.add(book);
        }
        return bookList;
    }
}
