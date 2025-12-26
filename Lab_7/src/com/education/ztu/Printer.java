package com.education.ztu;

public class Printer implements Runnable {
    private final SharedData dataContainer;

    public Printer(SharedData dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String receivedData = dataContainer.getData();

                if (receivedData != null) {
                    displayReceivedData(receivedData);
                }
            }
        } catch (Exception ex) {
            displayInterruptionMessage();
        }
    }

    private void displayReceivedData(String data) {
        System.out.println("Printer отримав: " + data);
    }

    private void displayInterruptionMessage() {
        System.out.println("Printer перервано");
    }
}