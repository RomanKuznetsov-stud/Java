package com.education.ztu;
public class SumCounter implements Runnable {
    private final int[] dataArray;
    private final int beginIndex;
    private final int finishIndex;
    private volatile long calculatedSum = 0;
    public SumCounter(int[] dataArray, int beginIndex, int finishIndex) {
        this.dataArray = dataArray;
        this.beginIndex = beginIndex;
        this.finishIndex = finishIndex;
    }
    @Override
    public void run() {
        calculateRangeSum();
    }

    private void calculateRangeSum() {
        long accumulator = 0;
        for (int index = beginIndex; index < finishIndex; index++) {
            accumulator += dataArray[index];
        }
        calculatedSum = accumulator;
    }

    public long getSum() {
        return calculatedSum;
    }
}