package project.libraryserver.Database;


import project.libraryserver.Consts.DATA;

import java.sql.*;

public class MySql {
    private static Connection connection;

    private MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.getJdbcUrl(),
                    DATA.getJdbcUser(),
                    DATA.getJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    private static final class InstanceHolder {
        private static final MySql instance = new MySql();
    }

    public static MySql getInstance() {
        return InstanceHolder.instance;
    }

    public static boolean QueryCheckLogIn(String email, String password) throws SQLException {
        String query = "SELECT count(*) FROM accounts WHERE email = ? and password = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            if (rs.getInt(1) == 1) return true;
        }
        return false;
    }

    public static boolean checkAccountByEmail(String email) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM accounts WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            existed = rs.getBoolean(1);
        }
        return existed;
    }

    public static boolean checkAccountByUsername(String username) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            existed = rs.getBoolean(1);
        }
        return existed;
    }

    public static void addNewAccount(String email, String username, String password) throws SQLException {
        String SQL = "INSERT INTO accounts(email, username, password) VALUES(?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, email);
        stmt.setString(2, username);
        stmt.setString(3, password);
        int status = stmt.executeUpdate();
//        System.out.println(status);
    }

    public static void addNewBook(String imageURL, String title, String author, String publishedDate, String categories, String ISBN_13, int price, String description) throws SQLException {
        String SQL = "INSERT INTO books(imagePreview, title, author, publishedDate, categories, ISBN_13, price, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, imageURL);
        stmt.setString(2, title);
        stmt.setString(3, author);
        stmt.setString(4, publishedDate);
        stmt.setString(5, categories);
        stmt.setString(6, ISBN_13);
        stmt.setInt(7, price);
        stmt.setString(8, description);
        int status = stmt.executeUpdate();
//        System.out.println(status);
    }
}
