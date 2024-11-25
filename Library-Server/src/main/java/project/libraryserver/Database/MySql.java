package project.libraryserver.Database;

import project.libraryserver.Consts.DATA;

import java.sql.*;
import java.util.ArrayList;

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

    public static boolean checkAccountByEmail(String email) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM user WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            existed = rs.getBoolean(1);
        }
        return existed;
    }

    public boolean CreateNewUser(String firstName, String lastName, String email, String password) throws SQLException {
        boolean existed = MySql.checkAccountByEmail(email);
        if (existed) {
            System.out.println("Email already exists");
            return false;
        }
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO user(first_name, last_name, email, status) " + "VALUES(?, ?, ?, ?)"
                );

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, "ON");
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
        return true;
    }

    public static void addNewBook(String title, String author, String ISBN, double price, String publishedDate, ArrayList<String> categories, String imagePreview, String description) throws SQLException {

    }
}
