package com.education.ztu;

public class ArithmeticProgressionSync implements Runnable {
    private static volatile int counter = 1;
    private static final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            displayNextNumber();
        }
    }

    private void displayNextNumber() {
        synchronized (lock) {
            System.out.print(counter + " ");
            counter++;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + ex.getMessage());
        }
    }
}