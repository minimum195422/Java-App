package project.englishapp.JDBC;

import java.sql.*;

public class AppJDBC {
    public Connection connection;

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/englishappdatabase",
                    "minimum195422",
                    "Humhayha12#");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");

            while(resultSet.next()) {
                System.out.print(resultSet.getString("id") + " ");
                System.out.print(resultSet.getString("firstname") + " ");
                System.out.print(resultSet.getString("lastname") + " ");
                System.out.print(resultSet.getString("mail") + " ");
                System.out.println(resultSet.getString("accounttype") + " ");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
