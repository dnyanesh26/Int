package org.example.MultiThreading.Claude;

import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.*;

public class ForkJoinDemo {

    // RecursiveTask — returns a value
    static class MergeSort extends RecursiveTask<int[]> {
        private final int[] array;
        private static final int THRESHOLD = 512;

        MergeSort(int[] array) { this.array = array; }

        @Override
        protected int[] compute() {
            if (array.length <= THRESHOLD) {
                return sequentialSort(array); // Base case
            }

            int mid = array.length / 2;
            int[] left  = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            MergeSort leftTask  = new MergeSort(left);
            MergeSort rightTask = new MergeSort(right);

            leftTask.fork();             // Async execution of left
            int[] rightResult = rightTask.compute(); // Current thread does right
            int[] leftResult  = leftTask.join();     // Wait for left

            return merge(leftResult, rightResult);
        }

        private int[] sequentialSort(int[] arr) {
            Arrays.sort(arr); return arr;
        }

        private int[] merge(int[] l, int[] r) {
            int[] result = new int[l.length + r.length];
            int i = 0, j = 0, k = 0;
            while (i < l.length && j < r.length)
                result[k++] = l[i] <= r[j] ? l[i++] : r[j++];
            while (i < l.length) result[k++] = l[i++];
            while (j < r.length) result[k++] = r[j++];
            return result;
        }
    }

    // RecursiveAction — no return value
    static class ParallelArrayFill extends RecursiveAction {
        private final int[] array;
        private final int lo, hi;
        private static final int THRESHOLD = 1000;

        ParallelArrayFill(int[] array, int lo, int hi) {
            this.array = array; this.lo = lo; this.hi = hi;
        }

        @Override
        protected void compute() {
            if (hi - lo <= THRESHOLD) {
                for (int i = lo; i < hi; i++) array[i] = i * i;
            } else {
                int mid = (lo + hi) / 2;
                invokeAll( // Fork both and wait
                        new ParallelArrayFill(array, lo, mid),
                        new ParallelArrayFill(array, mid, hi)
                );
            }
        }
    }

    public static void main(String[] args) {
        // ForkJoinPool.commonPool() used by parallel streams & CompletableFuture by default
        ForkJoinPool pool = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors()
        );

        int[] data = new int[1_000_000];
        new Random().ints().limit(data.length).toArray();

        int[] sorted = pool.invoke(new MergeSort(data));
        System.out.println("Sorted " + sorted.length + " elements");

        int[] filled = new int[10_000];
        pool.invoke(new ParallelArrayFill(filled, 0, filled.length));
        System.out.println("filled[100] = " + filled[100]); // 10000

        pool.shutdown();
    }
}