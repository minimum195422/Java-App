package project.libraryserver.Database;


import org.mindrot.jbcrypt.BCrypt;
import project.libraryserver.Consts.DATA;

import java.sql.*;

public class MySql {
    private static MySql instance;
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

    public static MySql getInstance() {
        if (instance == null) {
            instance = new MySql();
        }
        return instance;
    }

    public boolean QueryCheckNormalLogin(String email, String password) throws SQLException {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT p.password " +
                            "FROM user u join passwords p on u.id = p.user_id " +
                            "WHERE u.email = ?;"
                );

        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
//            update using BCrypt to hash or check password
//            BCrypt.checkpw(password, rs.getString("password"))

            // compare user given password and password in database
            return password.equals(rs.getString("password"));
        } else {
            System.out.println("Server can not find login information");
            return false;
        }
    }

    public boolean QueryCheckGoogleLogin(String google_id, String email) {
        return false;
    }

    public void CreateNewUser() {

    }
//    public static void addNewAccount(String email, String username, String password) throws SQLException {
//        String SQL = "INSERT INTO accounts(email, username, password) VALUES(?, ?, ?)";
//        PreparedStatement stmt = connection.prepareStatement(SQL);
//        stmt.setString(1, email);
//        stmt.setString(2, username);
//        stmt.setString(3, password);
//        int status = stmt.executeUpdate();
////        System.out.println(status);
//    }
//
//    public static void addNewBook(String imageURL, String title, String author, String publishedDate, String categories, String ISBN_13, int price, String description) throws SQLException {
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
