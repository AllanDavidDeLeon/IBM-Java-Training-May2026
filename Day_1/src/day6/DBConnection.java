package day6;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "!Willump08";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}