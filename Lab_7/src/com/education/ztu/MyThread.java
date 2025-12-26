package com.education.ztu;
public class MyThread extends Thread {
    private static final int ITERATIONS_COUNT = 100;
    private static final String MESSAGE = "Я люблю програмувати!!!";

    @Override
    public void run() {
        for (int iteration = 0; iteration < ITERATIONS_COUNT; iteration++) {
            displayMessage();
        }
    }

    private void displayMessage() {
        System.out.println(MESSAGE);
    }
}