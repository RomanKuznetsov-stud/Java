package com.education.ztu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTasks {

    public static void run(List<Product> products) {
        System.out.println("\n----- Завдання 4 Stream API -----");
        System.out.println("\n1. Всі продукти:");
        products.stream()
                .forEach(System.out::println);

        System.out.println("\n2. Всі бренди:");
        products.stream()
                .map(Product::getBrand)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n3. 2 товари ціною < 1000:");
        products.stream()
                .filter(p -> p.getPrice() < 1000)
                .limit(2)
                .forEach(System.out::println);

        double totalStockValue = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getCount())
                .reduce(0.0, Double::sum);
        System.out.println("\n4. Загальна вартість товарів на складі: " + totalStockValue);

        Map<String, List<Product>> groupedByBrand = products.stream()
                .collect(Collectors.groupingBy(Product::getBrand));
        System.out.println("\n5. Товари, згруповані за брендом:");
        groupedByBrand.forEach((brand, list) -> {
            System.out.println("  " + brand + ":");
            list.forEach(p -> System.out.println("    " + p.getName()));
        });

        List<Product> sortedProducts = products.stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .collect(Collectors.toList());
        System.out.println("\n6. Товари, відсортовані за зростанням ціни:");
        sortedProducts.forEach(System.out::println);
    }
}