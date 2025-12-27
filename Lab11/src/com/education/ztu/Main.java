package com.education.ztu;

import com.education.ztu.dao.ProductDAO;
import com.education.ztu.entity.Product;

public class Main {
    public static void main(String[] args) {

        InventoryManager.runTasks();

        System.out.println("\n--- Task 6: DAO Demonstration ---");
        ProductDAO dao = new ProductDAO();

        dao.create(new Product("DAO Laptop", "Tech", "Dell", 1500.0));
        dao.create(new Product("DAO Mouse", "Tech", "Logitech", 25.0));

        System.out.println("Products in DB via DAO:");
        dao.findAll().forEach(System.out::println);
    }
}