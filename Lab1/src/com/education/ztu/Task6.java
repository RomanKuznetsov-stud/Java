package com.education.ztu;

import java.util.Scanner;
import java.util.Arrays;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість елементів (n): ");
        int n = scanner.nextInt();
        scanner.close();

        if (n <= 0) {
            System.out.println("Кількість елементів повинна бути більше 0.");
            return;
        }

        int[] fibonacci = new int[n];
        fibonacci[0] = 1;
        if (n > 1) fibonacci[1] = 1;

        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        int[] reversedFibonacci = new int[n];
        for (int i = 0; i < n; i++) {
            reversedFibonacci[i] = fibonacci[n - 1 - i];
        }

        System.out.println("Послідовність Фібоначчі: " + Arrays.toString(fibonacci));
        System.out.println("Зворотня послідовність Фібоначчі: " + Arrays.toString(reversedFibonacci));
    }
}