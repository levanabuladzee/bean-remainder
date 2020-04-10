package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    final String URL = "jdbc:postgresql://localhost/remainder";
    final String NAME = "postgres";
    final String PASSWORD = "admin123";

    private Connection connection = null;

    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
