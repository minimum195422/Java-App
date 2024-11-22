package project.libraryserver.Database;

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
        // Return false if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Set prepared statement structure
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT p.password " +
                            "FROM user u join passwords p on u.id = p.user_id " +
                            "WHERE u.email = ?;"
                );
        preparedStatement.setString(1, email);

        // query action
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

    public boolean QueryCheckGoogleLogin(String google_id, String email) throws SQLException {
        // Return false if id or email is empty
        if (google_id.isEmpty() || email.isEmpty()) {
            return false;
        }

        // Set prepared statement structure
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT count(*) " +
                            "FROM google " +
                            "WHERE id = ? and email = ?"
                );
        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, email);

        // query action
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) == 1;
        } else {
            System.out.println("Server can not find login information");
            return false;
        }
    }

    public void CreateNewUser(String firstName, String lastName, String email, String password) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO user(first_name, last_name, email) " + "VALUES(?, ?, ?)"
                );

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        int status = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(
                "SELECT id " +
                        "FROM user " +
                        "WHERE email = ?"
        );
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int user_id = rs.getInt(1);
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO passwords(user_id, password) " +
                            "VALUES(?, ?)"
            );
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, password);
            status = preparedStatement.executeUpdate();
        }
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
