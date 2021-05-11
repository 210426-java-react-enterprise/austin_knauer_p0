package com.revature.austinknauerp0.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBConnectionMaker {

    private static final JDBConnectionMaker JDBConnectionMaker = new JDBConnectionMaker();
    private Properties props = new Properties();

    private JDBConnectionMaker() {
        try {
            props.load(new FileReader("src/main/resources/connection.properties"));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JDBConnectionMaker getInstance() {
        return JDBConnectionMaker;
    }

    public Connection getConnection() {

        Connection conn = null;

        try {
            DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }
}
