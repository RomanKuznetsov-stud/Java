package com.education.ztu;

public class SharedData {
    private String storedData;
    private boolean isDataAvailable = false;

    public synchronized void setData(String inputData) {
        while (isDataAvailable) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        this.storedData = inputData;
        isDataAvailable = true;
        notifyAll();
    }

    public synchronized String getData() {
        while (!isDataAvailable) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        isDataAvailable = false;
        notifyAll();
        return storedData;
    }
}