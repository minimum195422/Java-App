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

//    public String getPasswordByEmail(String email) throws SQLException {
//        String SQL = "SELECT password FROM accounts WHERE email = ?";
//        PreparedStatement stmt =  connection.prepareStatement(SQL);
//        stmt.setString(1, email);
//        ResultSet rs = stmt.executeQuery();
//        String password = "";
//        while (rs.next()) {
//            password = rs.getString(1);
//        }
//        return password;
//    }

//    public static boolean checkAccountByEmail(String email) throws SQLException {
//        String SQL = "SELECT COUNT(*) FROM user WHERE email = ?";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, email);
//        ResultSet rs = stmt.executeQuery();
//        boolean existed = false;
//        while (rs.next()) {
//            existed = rs.getBoolean(1);
//        }
//        return existed;
//    }

    public static ArrayList<String> getBookBySubstring(String substr) throws SQLException {
        ArrayList<String> bookList = new ArrayList<>();
        String SQL = "SELECT * FROM book WHERE title LIKE '%" + substr + "%'";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            bookList.add(rs.getString(1));
        }
        return bookList;
    }

    public static Book getBasicInfoOfBook(String name) throws SQLException {
        Book book = new Book();
        String SQL = "SELECT title, authors.name as author, image_preview "
                + "FROM book "
                + "JOIN book_authors ON book.book_id = book_authors.book_id "
                + "JOIN authors ON authors.author_id = book_authors.author_id "
                + "WHERE title LIKE '%" + name + "%'";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // title, author, image
            book = new Book(rs.getString(1), rs.getString(2), rs.getString(3));
        }
        return book;
    }
//
//    public boolean checkAccountByUsername(String username) throws SQLException {
//        String SQL = "SELECT COUNT(*) FROM accounts WHERE username = ?";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, username);
//        ResultSet rs = stmt.executeQuery();
//        boolean existed = false;
//        while (rs.next()) {
//            existed = rs.getBoolean(1);
//        }
//        return existed;
//    }
//
//    public void addNewAccount(String email, String username, String password) throws SQLException {
//        String SQL = "INSERT INTO accounts(email, username, password) VALUES(?, ?, ?)";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, email);
//        stmt.setString(2, username);
//        stmt.setString(3, password);
//        int status = stmt.executeUpdate();
////        System.out.println(status);
//    }
//
//    public void addNewBook(String imageURL, String title, String author, String publishedDate, String categories, String ISBN_13, int price, String description) throws SQLException {
//        String SQL = "INSERT INTO books(imagePreview, title, author, publishedDate, categories, ISBN_13, price, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, imageURL);
//        stmt.setString(2, title);
//        stmt.setString(3, author);
//        stmt.setString(4, publishedDate);
//        stmt.setString(5, categories);
//        stmt.setString(6, ISBN_13);
//        stmt.setInt(7, price);
//        stmt.setString(8, description);
//        int status = stmt.executeUpdate();
////        System.out.println(status);
//    }
}
