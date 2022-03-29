package com.company.core.threads;

import java.util.concurrent.*;

class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 8};
        int[] arr2 = {9, 1, 3, 6, 1, 3, 2, 2, 3, 4};

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        System.out.println("---- 1 ---");
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int resultSum = 0;
                for (int i : arr1) {
                    resultSum += i;
                    System.out.println("t1: " + i);
                }
                return resultSum;
            }
        };
        Future<Integer> f1 = executorService.submit(callable);

        System.out.println("---- 2 ---");
        Future<?> f2 = executorService2.submit(() -> {
            int resultSum = 0;
            for (int i : arr2) {
                resultSum += i;
                System.out.println("t2: " + i);
            }
            return resultSum;
        });

        System.out.println("Суммы масивов: " + f1.get() + ",  " + f2.get());

        executorService2.shutdown();
        executorService.shutdown();
    }
}
