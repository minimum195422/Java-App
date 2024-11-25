package project.libraryserver.Database;

import project.libraryserver.Consts.DATA;
import project.libraryserver.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean CreateNewNormalUser(
            String firstName,
            String lastName,
            String email,
            String password) throws SQLException {

        if (CheckExistEmailOnNormalUser(email)) {
            System.out.println("Email already exists");
            return false;
        }
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO user(first_name, last_name, email, status) "
                            + "VALUES(?, ?, ?, ?)"
                );

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, "ON");
        if (!preparedStatement.execute()) return false;

        preparedStatement.clearBatch();
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
            return preparedStatement.execute();
        }
        return true;
    }

    public boolean CreateNewGoogleUser(
            String google_id,
            String given_name,
            String family_name,
            String email,
            String picture) throws SQLException {

        if (CheckExistEmailOnGoogleUser(
                google_id, given_name, family_name, email, picture
        )) {
            System.out.println("Email already exists");
            return false;
        }

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO google (id, given_name, family_name, email, picture_link) "
                                + "VALUES(?, ?, ?, ?, ?)"
                );

        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, given_name);
        preparedStatement.setString(3, family_name);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, picture);

        return preparedStatement.execute();
    }

    public static boolean CheckExistEmailOnNormalUser(String email) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM user WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            if (rs.getInt(1) == 1) existed = true;
        }
        return existed;
    }

    public static boolean CheckExistEmailOnGoogleUser(
        String google_id,
        String given_name,
        String family_name,
        String email,
        String picture) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT COUNT(*) FROM google "
                            + "WHERE id = ? AND given_name = ? "
                            + "AND family_name = ? AND email = ? and picture_link = ?"
                );
        preparedStatement.setString(1, google_id);
        preparedStatement.setString(2, given_name);
        preparedStatement.setString(3, family_name);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, picture);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            if (rs.getInt(1) == 1) return true;
        }
        return false;
    }

    public List<User> GetUserList() throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "SELECT " +
                                "u.id, " +
                                "u.first_name, " +
                                "u.last_name, " +
                                "u.email, " +
                                "u.status, " +
                                "p.password " +
                            "FROM " +
                                "user u " +
                                "JOIN passwords p ON u.id = p.user_id;"
                );
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            list.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }

        return list;
    }

    public void UpdateUser(
            int user_id,
            String new_firstName,
            String new_lastName,
            String new_email,
            String new_password,
            String new_status
    ) throws SQLException{

        // update bảng passwords trước
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "UPDATE passwords SET password = ? WHERE user_id = ?;"
                );
        preparedStatement.setString(1, new_password);
        preparedStatement.setInt(2, user_id);

        if(preparedStatement.executeUpdate() == 0)
            throw new SQLException("Fail to update password table");

        // sau khi update trên passwords
        // tiến hành update trên user
        preparedStatement.clearBatch();
        preparedStatement = connection.prepareStatement(
                "UPDATE user "
                + "SET first_name = ?, last_name = ?, email = ?, status = ? "
                + "WHERE id = ?;"
        );
        preparedStatement.setString(1, new_firstName);
        preparedStatement.setString(2, new_lastName);
        preparedStatement.setString(3, new_email);
        preparedStatement.setString(4, new_status);
        preparedStatement.setInt(5, user_id);

        if (preparedStatement.executeUpdate() == 0)
            throw new SQLException("Fail to update user table");
    }

    public void DeleteUser(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM passwords WHERE user_id = ?;"
        );
        preparedStatement.setInt(1, id);
        if (preparedStatement.executeUpdate() == 0)
            throw new SQLException("Can not delete from table password");

        preparedStatement.clearBatch();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM user WHERE id = ?;"
        );
        preparedStatement.setInt(1, id);
        if (preparedStatement.executeUpdate() == 0)
            throw new SQLException("Can not delete from table user");
    }
}
