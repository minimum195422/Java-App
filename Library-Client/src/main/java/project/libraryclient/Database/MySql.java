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

    public static Book getBasicInfoOfBook(String name) throws SQLException {
        Book book = new Book();
        String SQL = "SELECT books.book_id as id, title, authors.name as author, image_preview "
                + "FROM books "
                + "JOIN book_authors ON books.book_id = book_authors.book_id "
                + "JOIN authors ON authors.author_id = book_authors.author_id "
                + "WHERE title LIKE '%" + name + "%'";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // title, author, image
            book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        return book;
    }
    
}
