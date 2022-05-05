package com.belhard.bookstore.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ResourceReader {

    private static final String LOCAL_SQL = "src/com/belhard/bookstore/properties/db.properties";
    private static final String ELEPHANT_SQL = "src/com/belhard/bookstore/properties/elephantsqlDb.properties";
    private static Properties properties;

    public ResourceReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(LOCAL_SQL));
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDbUser() {
        String user = properties.getProperty("db.user");
        return user;
    }

    public String getDbPassword() {
        String password = properties.getProperty("db.password");
        return password;
    }

    public String getDbUrl() {
        String url = properties.getProperty("db.url");
        return url;
    }

}
