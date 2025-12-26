package com.education.ztu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int ARRAY_SIZE = 1000000;
    private static final int THREAD_COUNT = 5;
    private static final int SLEEP_DURATION = 2000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMainMenu();

            try {
                int userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice) {
                    case 0:
                        isRunning = false;
                        System.out.println("Завершення програми...");
                        break;
                    case 2:
                        System.out.println("\n----------Завдання 2: Робота з класом Thread----------");
                        executeTask2();
                        break;
                    case 3:
                        System.out.println("\n---------Завдання 3: Робота з інтерфейсом Runnable---------");
                        executeTask3();
                        break;
                    case 4:
                        System.out.println("\n---------Завдання 4: Синхронізовані методи---------");
                        executeTask4();
                        break;
                    case 5:
                        System.out.println("\n---------Завдання 5: Синхронізовані блоки---------");
                        executeTask5();
                        break;
                    case 6:
                        System.out.println("\n---------Завдання 6: Взаємодія потоків з wait() і notify()---------");
                        executeTask6();
                        break;
                    case 7:
                        System.out.println("\n---------Завдання 7: Порівняння однопоточного і багатопоточного обчислення---------");
                        executeTask7();
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                        continue;
                }

                if (isRunning && userChoice >= 2 && userChoice <= 7) {
                    waitForUserInput(input);
                }

            } catch (Exception ex) {
                System.out.println("Помилка вводу. Спробуйте ще раз.");
                input.nextLine();
            }
        }

        input.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n---------Лабораторна робота №7: Багатопоточне програмування в Java---------");
        System.out.println("Виберіть завдання для запуску (2-7) або введіть 0 для виходу:");
    }

    private static void waitForUserInput(Scanner input) {
        System.out.println("\nНатисніть Enter для продовження...");
        input.nextLine();
    }

    private static void executeTask2() {
        MyThread customThread = new MyThread();

        System.out.println("Стан потоку: NEW");
        displayThreadInfo(customThread);

        customThread.setName("MyCoolThread");
        customThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Оновлене ім'я потоку: " + customThread.getName());
        System.out.println("Оновлений пріоритет потоку: " + customThread.getPriority());

        customThread.start();
        System.out.println("Стан потоку: RUNNING");

        try {
            customThread.join();
        } catch (InterruptedException ex) {
            System.err.println("Потік перервано: " + ex.getMessage());
        }

        System.out.println("Стан потоку: TERMINATED");

        Thread currentMainThread = Thread.currentThread();
        System.out.println("Ім'я головного потоку: " + currentMainThread.getName());
        System.out.println("Пріоритет головного потоку: " + currentMainThread.getPriority());
    }

    private static void displayThreadInfo(Thread thread) {
        System.out.println("Ім'я потоку: " + thread.getName());
        System.out.println("Пріоритет потоку: " + thread.getPriority());
        System.out.println("Чи живий потік: " + thread.isAlive());
        System.out.println("Чи є потік демоном: " + thread.isDaemon());
    }

    private static void executeTask3() {
        Thread workerThread1 = new Thread(new MyRunnable(), "Thread-1");
        Thread workerThread2 = new Thread(new MyRunnable(), "Thread-2");
        Thread workerThread3 = new Thread(new MyRunnable(), "Thread-3");

        workerThread1.start();
        workerThread2.start();
        workerThread3.start();

        try {
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException ex) {
            System.err.println("Головний потік перервано: " + ex.getMessage());
        }

        interruptThreads(workerThread1, workerThread2, workerThread3);
    }

    private static void interruptThreads(Thread... threads) {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static void executeTask4() {
        Thread syncThread1 = new Thread(new ArithmeticProgressionSync(), "Thread-1");
        Thread syncThread2 = new Thread(new ArithmeticProgressionSync(), "Thread-2");
        Thread syncThread3 = new Thread(new ArithmeticProgressionSync(), "Thread-3");

        resetCounterField("ArithmeticProgressionSync", "result");

        syncThread1.start();
        syncThread2.start();
        syncThread3.start();

        waitForThreadsCompletion(syncThread1, syncThread2, syncThread3);
    }

    private static void executeTask5() {
        Thread blockThread1 = new Thread(new ArithmeticProgressionSyncBlock(), "->");
        Thread blockThread2 = new Thread(new ArithmeticProgressionSyncBlock(), "->");
        Thread blockThread3 = new Thread(new ArithmeticProgressionSyncBlock(), "->");

        resetCounterField("ArithmeticProgressionSyncBlock", "result");

        blockThread1.start();
        blockThread2.start();
        blockThread3.start();

        waitForThreadsCompletion(blockThread1, blockThread2, blockThread3);
    }

    private static void resetCounterField(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName("com.education.ztu." + className);
            java.lang.reflect.Field counterField = clazz.getDeclaredField(fieldName);
            counterField.setAccessible(true);
            counterField.set(null, 1);
        } catch (Exception ex) {
            System.err.println("Помилка скидання лічильника: " + ex.getMessage());
        }
    }

    private static void waitForThreadsCompletion(Thread... threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.err.println("Потік перервано при очікуванні: " + ex.getMessage());
            }
        }
    }

    private static void executeTask6() {
        SharedData dataContainer = new SharedData();

        Thread dataReader = new Thread(new Reader(dataContainer), "Reader");
        Thread dataPrinter = new Thread(new Printer(dataContainer), "Printer");

        dataReader.start();
        dataPrinter.start();

        try {
            dataReader.join();
            dataPrinter.interrupt();
            dataPrinter.join();
        } catch (InterruptedException ex) {
            System.err.println("Помилка при завершенні потоків: " + ex.getMessage());
        }
    }

    private static void executeTask7() throws Exception {
        int[] numbersArray = generateRandomArray(ARRAY_SIZE);

        long singleThreadResult = measureSingleThreadPerformance(numbersArray);
        long multiThreadRunnableResult = measureMultiThreadRunnablePerformance(numbersArray);
        long multiThreadCallableResult = measureMultiThreadCallablePerformance(numbersArray);
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random randomGenerator = new Random();

        for (int index = 0; index < size; index++) {
            array[index] = randomGenerator.nextInt(10);
        }

        return array;
    }

    private static long measureSingleThreadPerformance(int[] numbers) {
        long startTime = System.currentTimeMillis();
        long sum = computeSumSingleThread(numbers);
        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println("Однопоточне обчислення:");
        System.out.println("Сума: " + sum);
        System.out.println("Час виконання: " + executionTime + " мс");

        return sum;
    }

    private static long measureMultiThreadRunnablePerformance(int[] numbers) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long sum = computeSumMultiThreadRunnable(numbers, THREAD_COUNT);
        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println("\nБагатопоточне обчислення (Runnable):");
        System.out.println("Сума: " + sum);
        System.out.println("Час виконання: " + executionTime + " мс");

        return sum;
    }

    private static long measureMultiThreadCallablePerformance(int[] numbers) throws Exception {
        long startTime = System.currentTimeMillis();
        long sum = computeSumMultiThreadCallable(numbers, THREAD_COUNT);
        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println("\nБагатопоточне обчислення (Callable):");
        System.out.println("Сума: " + sum);
        System.out.println("Час виконання: " + executionTime + " мс");

        return sum;
    }

    private static long computeSumSingleThread(int[] numbers) {
        long totalSum = 0;
        for (int value : numbers) {
            totalSum += value;
        }
        return totalSum;
    }

    private static long computeSumMultiThreadRunnable(int[] numbers, int threadCount) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        int chunkSize = numbers.length / threadCount;
        SumCounter[] sumCounters = new SumCounter[threadCount];

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int startIndex = threadIndex * chunkSize;
            int endIndex = (threadIndex == threadCount - 1) ? numbers.length : (threadIndex + 1) * chunkSize;

            sumCounters[threadIndex] = new SumCounter(numbers, startIndex, endIndex);
            threadPool.execute(sumCounters[threadIndex]);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);

        long finalSum = 0;
        for (SumCounter counter : sumCounters) {
            finalSum += counter.getSum();
        }

        return finalSum;
    }

    private static long computeSumMultiThreadCallable(int[] numbers, int threadCount) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        int chunkSize = numbers.length / threadCount;
        List<Future<Long>> futureResults = new ArrayList<>();

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int startIndex = threadIndex * chunkSize;
            int endIndex = (threadIndex == threadCount - 1) ? numbers.length : (threadIndex + 1) * chunkSize;

            futureResults.add(threadPool.submit(new SumCalculator(numbers, startIndex, endIndex)));
        }

        threadPool.shutdown();

        long finalSum = 0;
        for (Future<Long> future : futureResults) {
            finalSum += future.get();
        }

        return finalSum;
    }
}