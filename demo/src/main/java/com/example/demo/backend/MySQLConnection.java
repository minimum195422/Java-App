package com.example.demo.backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection{
    public static Connection connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library_database";
            String user = "root";
            String password = "amogus69420";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
