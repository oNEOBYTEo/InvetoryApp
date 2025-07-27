package com.inventory;

import com.inventory.db.DBInitializer;
import com.inventory.model.Product;
import com.inventory.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBInitializer.initialize();

        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add product");
            System.out.println("2. List products");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    productService.create(new Product(name, qty, price));
                    System.out.println("Product added!");
                    break;

                case 2:
                    List<Product> products = productService.readAll();
                    if (products.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product p : products) {
                            System.out.printf("ID: %d | Name: %s | Quantity: %d | Price: %.2f\n",
                                    p.getId(), p.getName(), p.getQuantity(), p.getPrice());
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New quantity: ");
                    int newQty = scanner.nextInt();
                    System.out.print("New price: ");
                    double newPrice = scanner.nextDouble();
                    productService.update(new Product(idToUpdate, newName, newQty, newPrice));
                    System.out.println("Product updated!");
                    break;

                case 4:
                    System.out.print("ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    productService.delete(idToDelete);
                    System.out.println("Product deleted!");
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);

        scanner.close();
    }
}