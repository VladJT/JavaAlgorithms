package com.company.core.threads;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


class ArraysExample {
    private static class MyTime {
        private long sTime = 0;

        public void start() {
            sTime = System.currentTimeMillis();
        }

        public void end() {
            sTime = System.currentTimeMillis() - sTime;
        }

        public void printTime() {
            System.out.println("Операция заняла: " + sTime + " милисек.");
        }
    }

    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;
    private static MyTime timer = new MyTime();


    public static void main(String[] args) {
        doInMainThread();// TEST main Thread
        doWithExecService();// TEST ExecutorService
        doInThreads();// TEST Threads
    }

    private static void doInThreads() {
        System.out.println("-- Метод 3. Через Threads --");
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        timer.start();

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                arr1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            int curIndexInArr = 0;
            for (int i = 0; i < HALF; i++) {
                curIndexInArr = i + HALF;
                arr2[i] = (float) (arr[curIndexInArr] * Math.sin(0.2f + curIndexInArr / 5) * Math.cos(0.2f + curIndexInArr / 5) *
                        Math.cos(0.4f + curIndexInArr / 2));
            }
        });

        t1.setPriority(10);
        t2.setPriority(10);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        timer.end();
        timer.printTime();
        System.out.println(arr[10]);
        System.out.println(arr[SIZE - 1]);
        System.out.println();
    }


    private static void doWithExecService() {
        System.out.println("-- Метод 2. Через ExecutorService --");
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        timer.start();
        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.execute(() -> {
            for (int i = 0; i < HALF; i++) {
                arr1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(() -> {
            int curIndexInArr = 0;
            for (int i = 0; i < HALF; i++) {
                curIndexInArr = i + HALF;
                arr2[i] = (float) (arr[curIndexInArr] * Math.sin(0.2f + curIndexInArr / 5) * Math.cos(0.2f + curIndexInArr / 5) *
                        Math.cos(0.4f + curIndexInArr / 2));
            }
        });

        executorService1.shutdown();
        executorService2.shutdown();
        try {
            if (!executorService1.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService1.shutdownNow();
            }
            if (!executorService2.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService2.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }// ждем завершения ExecService

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        timer.end();
        timer.printTime();
        System.out.println(arr[10]);
        System.out.println(arr[SIZE - 1]);
        System.out.println();
    }


    private static void doInMainThread() {
        System.out.println("-- Метод 1. работа через Main Thread --");
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        timer.start();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        timer.end();
        timer.printTime();
        System.out.println("Проверочные значения:");
        System.out.println(arr[10]);
        System.out.println(arr[SIZE - 1]);
        System.out.println();
    }

}
