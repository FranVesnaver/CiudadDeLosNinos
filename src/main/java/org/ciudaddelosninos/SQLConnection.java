package org.ciudaddelosninos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/ciudaddelosninos";
    private static final String user = "admin";
    private static final String password = "vilar123";

    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() {
        return connection;
    }
}
