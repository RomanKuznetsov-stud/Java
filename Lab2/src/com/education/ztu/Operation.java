package com.education.ztu;

public class Operation {
    public static double addition(double... numbers) {
        double sum = 0;
        for (double n : numbers)
            sum += n;
        return sum;
    }

    public static double subtraction(double... numbers) {
        if (numbers.length == 0)
            return 0;
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) result -= numbers[i];
        return result;
    }

    public static double multiplication(double... numbers) {
        double result = 1;
        for (double n : numbers) result *= n;
        return result;
    }

    public static double division(double... numbers) {
        if (numbers.length == 0)
            return 0;
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                System.out.println("Warning: Division by zero!");
                return Double.NaN;
            }
            result /= numbers[i];
        }
        return result;
    }

    public static double average(double... numbers) {
        if (numbers.length == 0)
            return 0;
        return addition(numbers) / numbers.length;
    }

    public static double maximum(double... numbers) {
        if (numbers.length == 0)
            return Double.NaN;
        double max = numbers[0];
        for (double n : numbers) if (n > max) max = n;
        return max;
    }

    public static double minimum(double... numbers) {
        if (numbers.length == 0)
            return Double.NaN;
        double min = numbers[0];
        for (double n : numbers) if (n < min) min = n;
        return min;
    }
}
