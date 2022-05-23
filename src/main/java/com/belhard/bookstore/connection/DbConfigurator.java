package com.belhard.bookstore.connection;

import com.belhard.bookstore.util.ResourceReader;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfigurator {
//    private static ResourceReader resourceReader = new ResourceReader();
    private static Connection connection;


    @Value("${url}")
    private static String url;
    @Value("username")
    private static String user;
    @Value("password")
    private static String password;
    static Logger logger = LogManager.getLogger();

    @PostConstruct
    public static void initDbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("Database connection.");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
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