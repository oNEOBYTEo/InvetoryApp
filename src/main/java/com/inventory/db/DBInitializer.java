package com.inventory.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    public static void initialize() {
        try (Connection connection = DBConnection.getConnection()) {
            if (!doesTableExist(connection, "products")) {
                createProductsTable(connection);
                System.out.println("Table 'products' created successfully.");
            } else {
                System.out.println("Table 'products' already exists.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    private static boolean doesTableExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        try (ResultSet result = metadata.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return result.next();
        }
    }

    private static void createProductsTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE products (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                quantity INT NOT NULL,
                price DOUBLE NOT NULL
            )
        """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

}
