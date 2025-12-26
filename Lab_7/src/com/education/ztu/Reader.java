package com.education.ztu;

import java.util.Scanner;

public class Reader implements Runnable {
    private static final String EXIT_COMMAND = "exit";
    private static final int SLEEP_DURATION = 1000;

    private final SharedData dataContainer;
    private final Scanner inputScanner;

    public Reader(SharedData dataContainer) {
        this.dataContainer = dataContainer;
        this.inputScanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        try {
            displayInstructions();

            while (!Thread.currentThread().isInterrupted()) {
                String userInput = inputScanner.nextLine();

                if (isExitCommand(userInput)) {
                    handleExit();
                    return;
                }

                dataContainer.setData(userInput);
                Thread.sleep(SLEEP_DURATION);
            }
        } catch (InterruptedException ex) {
            displayInterruptionMessage();
        } finally {
            closeScanner();
        }
    }

    private void displayInstructions() {
        System.out.println("Введіть дані (для завершення введіть 'exit'):");
    }

    private boolean isExitCommand(String input) {
        return EXIT_COMMAND.equalsIgnoreCase(input);
    }

    private void handleExit() {
        System.out.println("Завершення програми...");
        Thread.currentThread().interrupt();
    }

    private void displayInterruptionMessage() {
        System.out.println("Reader перервано");
    }

    private void closeScanner() {
        inputScanner.close();
    }
}