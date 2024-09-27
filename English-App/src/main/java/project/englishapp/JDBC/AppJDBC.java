package project.englishapp.JDBC;

import project.englishapp.Consts.JDBCData;

import java.sql.*;

public class AppJDBC {

    private static AppJDBC appJDBC;

    private static Connection connection;

    private AppJDBC() throws SQLException {
        connection = DriverManager.getConnection(JDBCData.getUrl(), JDBCData.getUser(), JDBCData.getPassword());
    }

    public static AppJDBC getInstance() throws SQLException {
        if (appJDBC == null) {
            appJDBC = new AppJDBC();
        }
        return appJDBC;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet QueryLoginUserMail(String InputMail, String PassWord) throws SQLException {
        String query = "select count(*) from user u join password p on u.id = p.id where u.mail = ? and p.password = ?;";
        PreparedStatement preparedStatement = AppJDBC.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, InputMail);
        preparedStatement.setString(2, PassWord);
        return preparedStatement.executeQuery();
    }
}
