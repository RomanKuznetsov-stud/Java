package com.education.ztu;

import java.util.*;
import java.text.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("========== Task 2 ==========");
        String s = "I learn Java!!!";
        System.out.println("Last char: " + s.charAt(s.length() - 1));
        System.out.println("Ends with !!!: " + s.endsWith("!!!"));
        System.out.println("Starts with 'I learn ': " + s.startsWith("I learn "));
        System.out.println("Contains 'Java': " + s.contains("Java"));
        System.out.println("Index of 'Java': " + s.indexOf("Java"));
        System.out.println("Replace a->o: " + s.replace('a', 'o'));
        System.out.println("Uppercase: " + s.toUpperCase());
        System.out.println("Lowercase: " + s.toLowerCase());
        System.out.println("Cut 'Java': " + s.substring(0, 8) + s.substring(12));

        System.out.println("\n========== Task 3 ==========");
        int a = 4, b = 36;
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(" + ").append(b).append(" = ").append(a + b).append("\n");
        sb.append(a).append(" - ").append(b).append(" = ").append(a - b).append("\n");
        sb.append(a).append(" * ").append(b).append(" = ").append(a * b).append("\n");

        System.out.println("\nStringBuilder initial:\n" + sb);

        StringBuilder sb2 = new StringBuilder(sb);
        int idx;
        while ((idx = sb2.indexOf("=")) != -1) {
            sb2.deleteCharAt(idx);
            sb2.insert(idx, "рівно");
        }
        System.out.println("Using insert/delete:\n" + sb2);

        StringBuilder sb3 = new StringBuilder(sb);
        while ((idx = sb3.indexOf("=")) != -1) {
            sb3.replace(idx, idx + 1, "рівно");
        }
        System.out.println("Using replace():\n" + sb3);

        System.out.println("Reversed:\n" + sb.reverse());
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());

        System.out.println("\n========== Task 4 ==========");
        Formatter f = new Formatter();
        f.format("Дата та час покупки: %s\n", "24.07.2023 11:19:10");
        f.format("===========================================\n");
        f.format("№ %-10s %-15s %s\n", "Товар", "Категорія", "Ціна");
        f.format("===========================================\n");

        String[][] items = {
                {"1.", "Джинси", "Жіночий одяг", "1500,78 ₴"},
                {"2.", "Спідниця", "Жіночий одяг", "1000,56 ₴"},
                {"3.", "Краватка", "Чоловічий одяг", "500,78 ₴"},
                {"4.", "Светр", "Чоловічий одяг", "1250,00 ₴"},
                {"5.", "Блуза", "Жіночий одяг", "899,50 ₴"},
                {"6.", "Шорти", "Чоловічий одяг", "550,00 ₴"},
                {"7.", "Сукня", "Жіночий одяг", "2800,00 ₴"},
                {"8.", "Піджак", "Чоловічий одяг", "3200,99 ₴"},
                {"9.", "Шарф", "Жіночий одяг", "250,50 ₴"},
                {"10.", "Ремінь", "Чоловічий одяг", "200,00 ₴"}
        };

        for (String[] row : items) {
            f.format("%s %-10s %-15s %s\n", row[0], row[1], row[2], row[3]);
        }

        f.format("===========================================\n");
        f.format("Разом: %s\n", "5030,34 ₴");

        System.out.println("\nReceipt:\n" + f);

        System.out.println("\n========== Task 5 ==========");
        Locale[] locales = {
                new Locale("uk", "UA"),
                new Locale("en", "US"),
                new Locale("de", "DE")
        };

        for (Locale loc : locales) {
            ResourceBundle rb = ResourceBundle.getBundle("data", loc);
            NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
            System.out.println("Locale: " + loc);
            System.out.println(rb.getString("title"));
            double price = 1000.95;
            System.out.println(rb.getString("price") + ": " + nf.format(price));
            System.out.println();
        }

        System.out.println("\n========== Task 6 ==========");
        LocalDateTime labStart = LocalDateTime.of(2025, 7, 24, 11, 35, 0);
        System.out.println("Day of week: " + labStart.getDayOfWeek());
        System.out.println("Day of year: " + labStart.getDayOfYear());
        System.out.println("Month: " + labStart.getMonth());
        System.out.println("Year: " + labStart.getYear());
        System.out.println("Hours: " + labStart.getHour());
        System.out.println("Minutes: " + labStart.getMinute());
        System.out.println("Seconds: " + labStart.getSecond());
        System.out.println("Is leap year: " + labStart.toLocalDate().isLeapYear());
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Is now after lab start: " + now.isAfter(labStart));
        System.out.println("Is now before lab start: " + now.isBefore(labStart));
        LocalDateTime modified = labStart.plusDays(10).minusHours(4);
        System.out.println("Modified date: " + modified);
    }
}
