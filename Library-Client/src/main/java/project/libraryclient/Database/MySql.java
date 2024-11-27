package project.libraryclient.Database;

import project.libraryclient.Class.Book;
import project.libraryclient.Consts.DATA;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<String> getBookBySubstring(String substr) throws SQLException {
        ArrayList<String> bookList = new ArrayList<>();
        String SQL = "SELECT title FROM books WHERE title LIKE '%" + substr + "%'";
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
        String SQL = "SELECT books.book_id as id, title, authors.name as author, image_preview "
                + "FROM books "
                + "JOIN book_authors ON books.book_id = book_authors.book_id "
                + "JOIN authors ON authors.author_id = book_authors.author_id "
                + "WHERE title = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        int prevId = 0;
        String prevTitle = "";
        ArrayList<String> prevAuthor = new ArrayList<>();
        String prevImage = "";
        while (rs.next()) {
            // title, author, image
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String author = rs.getString(3);
            String image = rs.getString(4);
//            System.out.println(title + " " + author + " " + image);
            if (prevId != id) {
                if (prevId > 0) {
                    book = new Book(prevId, prevTitle, prevAuthor, prevImage);
                    bookList.add(book);
                    prevAuthor.clear();
                }
            }
            prevId = id;
            prevTitle = title;
            prevAuthor.add(author);
            prevImage = image;
        }
        if (prevId > 0) {
            book = new Book(prevId, prevTitle, prevAuthor, prevImage);
            bookList.add(book);
        }
        return bookList;
    }

}
