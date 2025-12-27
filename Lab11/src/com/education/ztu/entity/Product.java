package com.education.ztu.entity;

public class Product {
    private int id;
    private String name;
    private String category;
    private String brand;
    private double price;

    public Product() {}

    public Product(String name, String category, String brand, double price) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Product[ID: %d, Name: %s, Category: %s, Brand: %s, Price: %.2f]",
                id, name, category, brand, price);
    }
}