package com.education.ztu;
public class MyRunnable implements Runnable {
    private static final int MAX_COUNT = 10000;
    private static final int DISPLAY_INTERVAL = 10;

    @Override
    public void run() {
        try {
            for (int counter = 0; counter <= MAX_COUNT; counter++) {
                if (Thread.interrupted()) {
                    displayMessage("Розрахунок завершено!");
                    return;
                }

                if (counter % DISPLAY_INTERVAL == 0) {
                    displayMessage(String.valueOf(counter));
                }
            }
        } catch (Exception ex) {
            displayMessage("Помилка: " + ex.getMessage());
        }
    }

    private void displayMessage(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}