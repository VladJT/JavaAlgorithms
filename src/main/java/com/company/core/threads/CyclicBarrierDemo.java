package com.company.core.threads;

// CyclicBarrier выполняет синхронизацию заданного количества потоков в одной точке. Как только
// заданное количество потоков заблокировалось (вызовами метода await()), с них одновременно снимается блокировка.

import java.util.concurrent.CyclicBarrier;

class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " готовится");
                    Thread.sleep(100 + (int) (3000 * Math.random()));
                    System.out.println("Поток " + w + " end");
                    cb.await();
                    System.out.println("Поток " + w + " запустился");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
