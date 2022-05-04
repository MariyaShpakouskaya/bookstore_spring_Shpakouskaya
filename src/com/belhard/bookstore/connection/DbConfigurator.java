package com.belhard.bookstore.connection;

import com.belhard.bookstore.util.ResourceReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfigurator {
    private static ResourceReader resourceReader = new ResourceReader();
    private static Connection connection;
    private static String url = resourceReader.getDbUrl();
    private static String user = resourceReader.getDbUser();
    private static String password = resourceReader.getDbPassword();

    public static void initDbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            initDbConnection();
        }
        return connection;
    }
}