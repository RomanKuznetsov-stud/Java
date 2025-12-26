package com.education.ztu;
public class ArithmeticProgressionSyncBlock implements Runnable {
    private static volatile int counter = 1;
    private static final Object SYNC_LOCK = new Object();

    @Override
    public void run() {
        for (int iteration = 0; iteration < 100; iteration++) {
            synchronized (SYNC_LOCK) {
                System.out.print(counter + " ");
                counter++;

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.err.println("Потік перервано: " + ex.getMessage());
                    break;
                }
            }
        }
    }
}