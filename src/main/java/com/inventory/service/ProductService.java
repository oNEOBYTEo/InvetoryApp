package com.inventory.service;

import com.inventory.db.DBConnection;
import com.inventory.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final Connection connection;

    public ProductService() {
        this.connection = DBConnection.getConnection();
    }

    public void create(Product product) {
        String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();
            System.out.println("Product created successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
        }
    }

    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Error reading products: " + e.getMessage());
        }

        return products;
    }

    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getId());
            int updated = stmt.executeUpdate();

            if (updated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with that ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();

            if (deleted > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with that ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }
}
