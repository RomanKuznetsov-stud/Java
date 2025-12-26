package com.education.ztu;
import java.util.concurrent.Callable;
public class SumCalculator implements Callable<Long> {
    private final int[] dataArray;
    private final int beginIndex;
    private final int finishIndex;

    public SumCalculator(int[] dataArray, int beginIndex, int finishIndex) {
        this.dataArray = dataArray;
        this.beginIndex = beginIndex;
        this.finishIndex = finishIndex;
    }
    @Override
    public Long call() {
        long totalSum = calculateRangeSum();
        return totalSum;
    }

    private long calculateRangeSum() {
        long accumulator = 0;
        for (int index = beginIndex; index < finishIndex; index++) {
            accumulator += dataArray[index];
        }
        return accumulator;
    }
}