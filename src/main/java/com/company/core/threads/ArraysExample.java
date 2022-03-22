package com.company.core.threads;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class MyTime {
    private long sTime = 0, eTime = 0;

    public void start() {
        sTime = System.currentTimeMillis();
    }

    public void end() {
        eTime = System.currentTimeMillis();
    }

    public void printTime() {
        System.out.println("Операция заняла: " + (eTime - sTime) + " милисек.");
    }
}


class MyArrThread extends Thread {
    float[] arr;
    int startIndex;

    public MyArrThread(float[] arr, int startIndex) {
        this.arr = arr;
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        for (int i = 0; i < ArraysExample.HALF + startIndex; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }
}

class ArraysExample {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static MyTime mt = new MyTime();


    public static void main(String[] args) {
        method1();
        method2();
    }


    private static void method2() {
        System.out.println("-- Метод 2 старт --");
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        mt.start();
        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

//        MyArrThread t1 = new MyArrThread(arr1,0);
//        MyArrThread t2 = new MyArrThread(arr2,HALF);
//        t1.start();
//        t2.start();

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        Future future1 = executorService1.submit(() -> {
            for (int i = 0; i < HALF; i++) {
                arr1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });

        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        Future future2 = executorService2.submit(() -> {
            int curIndexInArr = 0;
            for (int i = 0; i < HALF; i++) {
                curIndexInArr = i + HALF;
                arr2[i] = (float) (arr[curIndexInArr] * Math.sin(0.2f + curIndexInArr / 5) * Math.cos(0.2f + curIndexInArr / 5) *
                        Math.cos(0.4f + curIndexInArr / 2));
            }
        });

        try {
            while (future1.get() != null || (future2.get() != null)) {
                //ждем завершения окончания потоков
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        executorService1.shutdown();
        executorService2.shutdown();

        mt.end();
        mt.printTime();
        System.out.println("-- Метод 2 завершен --");
    }


    private static void method1() {
        System.out.println("-- Метод 1 старт --");
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        mt.start();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
            //   System.out.println(arr[i] + ", ");
        }
        mt.end();
        mt.printTime();
        System.out.println("-- Метод 1 завершен --");
    }

}
