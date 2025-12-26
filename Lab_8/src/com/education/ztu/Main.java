package com.education.ztu;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.IntStream;

public class Main {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public Main() {
    }

    public static void staticPrintMessage(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {

        System.out.println("------- Завдання 2. Власний функціональний інтерфейс ---------");
        Printable myPrinter = () -> System.out.println(" Це лямбда-вираз.");
        myPrinter.print();

        System.out.println("\n---");

        System.out.println("\n-------- Завдання 3. Вбудовані функціональні інтерфейси -------");

        Predicate<String> isNumeric = s -> {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
        System.out.println("а) '100' - чи число? " + isNumeric.test("100"));
        Predicate<String> isNumericAndPositive = isNumeric.and(s -> Double.parseDouble(s) > 0);
        System.out.println("а) '21' - число та > 0? " + isNumericAndPositive.test("21"));

        Consumer<String> showStart = message -> System.out.println("b) Пара розпочалася о 9:30");
        Consumer<String> showEnd = message -> System.out.println("b) Пара закінчилася о 9:50");
        Consumer<String> combinedConsumer = showStart.andThen(showEnd);
        combinedConsumer.accept("Повідомлення про пару");

        Supplier<String> upperCaseSupplier = () -> "Рядок у верхньому регістрі".toUpperCase();
        System.out.println("c) Supplier: " + upperCaseSupplier.get());

        Function<String, Integer> productOfNumbers = s -> Arrays.stream(s.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .reduce(1, (a, b) -> a * b);
        System.out.println("d) Добуток чисел '5 3 1': " + productOfNumbers.apply("5 3 1"));

        System.out.println("\n---");

        List<Product> products = Arrays.asList(
                new Product("Smartphone", "Apple", 999.99, 10),
                new Product("Tablet", "Lenovo", 450.0, 15),
                new Product("Smartwatch", "Garmin", 299.50, 25),
                new Product("Headphones", "Sony", 199.99, 40),
                new Product("Camera", "Canon", 850.0, 5),
                new Product("Speaker", "JBL", 120.0, 35)
        );
        StreamTasks.run(products);

        System.out.println("\n---");

        System.out.println("\n------- Завдання 5. Посилання на методи----------");

        Consumer<String> staticMethodRef = Main::staticPrintMessage;
        staticMethodRef.accept("1. Повідомлення через посилання на статичний метод.");

        Main mainInstance = new Main();
        Consumer<String> instanceMethodRef = mainInstance::printMessage;
        instanceMethodRef.accept("2. Повідомлення через посилання на метод екземпляра.");

        Supplier<Main> constructorRef = Main::new;
        Main newInstance = constructorRef.get();
        newInstance.printMessage("3. Об'єкт Main створено через посилання на конструктор.");

        Function<String, String> toUpperCaseRef = String::toUpperCase;
        System.out.println("4. 'hello' в верхньому регістрі: " + toUpperCaseRef.apply("hello"));

        System.out.println("\n---");

        System.out.println("\n------- Завдання 6. Optional ----------");

        int[] numbers = {10, 51, 22, 6, 1};

        Optional<Integer> maxNumber = IntStream.of(numbers)
                .boxed()
                .max(Integer::compare);

        String result = maxNumber
                .map(String::valueOf)
                .orElse("Числа відсутні");

        System.out.println("Максимальне значення в {10, 51, 22, 6, 1}: " + result);

        int[] emptyArray = {};
        Optional<Integer> maxEmpty = IntStream.of(emptyArray)
                .boxed()
                .max(Integer::compare);

        String resultEmpty = maxEmpty
                .map(String::valueOf)
                .orElse("Числа відсутні");

        System.out.println("Максимальне значення в {}: " + resultEmpty);
    }
}