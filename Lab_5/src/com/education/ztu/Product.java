package com.education.ztu;
import java.time.LocalDate;
import java.util.Objects;

public class Product implements Comparable<Product> {

    private String name;
    private double price;
    private LocalDate expirationDate;
    private int quantity;

    public Product(String name, double price, LocalDate expirationDate, int quantity) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s | price: %6.2f | qty: %3d",
                name, price, quantity
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return Double.compare(p.price, price) == 0 &&
                quantity == p.quantity &&
                Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}
