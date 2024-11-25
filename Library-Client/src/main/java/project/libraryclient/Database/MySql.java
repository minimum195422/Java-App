package project.libraryclient.Database;

import project.libraryclient.Consts.DATA;

import java.sql.*;

public class MySql {
    private static MySql instance;
    private final Connection connection;
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
}
