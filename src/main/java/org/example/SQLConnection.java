package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/ciudaddelosninos";
    private static final String usuario = "admin";
    private static final String contrasena = "vilar123";

    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(url, usuario, contrasena);
    }

    public static Connection getConnection() {
        return connection;
    }
}
