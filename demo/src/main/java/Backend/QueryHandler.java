package Backend;

import Frontend.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHandler {
    public static String getPasswordByUsername(String username) throws SQLException {
        String SQL = "SELECT password FROM accounts WHERE username = ?";
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        String password = "";
        while (rs.next()) {
            password = rs.getString(1);
        }
        return password;
    }

    public static boolean checkAccountByEmail(String email) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM accounts WHERE email = ?";
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
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
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
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
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
        stmt.setString(1, email);
        stmt.setString(2, username);
        stmt.setString(3, password);
        int status = stmt.executeUpdate();
//        System.out.println(status);
    }
}
