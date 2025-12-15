package com.education.ztu;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введіть перше число: ");
        int num1 = sc.nextInt();

        System.out.println("Введіть друге число: ");
        int num2 = sc.nextInt();

        int sum = num1 + num2;
        System.out.println("Результат: " + sum);

        AutoCloseable scanner;
    }
}