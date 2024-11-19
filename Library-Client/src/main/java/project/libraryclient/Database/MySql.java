package project.libraryclient.Database;

import project.libraryclient.Consts.DATA;

import java.sql.*;

public class MySql {
    private static MySql instance;
    private final Connection connection;

    public MySql() {
        try {
            connection = DriverManager.getConnection(
                    DATA.GetJdbcUrl(),
                    DATA.GetJdbcUser(),
                    DATA.GetJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
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

//    public boolean checkAccountByEmail(String email) throws SQLException {
//        String SQL = "SELECT COUNT(*) FROM accounts WHERE email = ?";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, email);
//        ResultSet rs = stmt.executeQuery();
//        boolean existed = false;
//        while (rs.next()) {
//            existed = rs.getBoolean(1);
//        }
//        return existed;
//    }
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
