package com.company.core.threads;


import java.util.concurrent.CountDownLatch;

// CountDownLatch позволяет потоку ожидать завершения операций, выполняющихся в других потоках.
// Режим ожидания запускается методом await(). При создании объекта определяется количество
// требуемых операций, после чего уменьшается при вызове метода countDown(). Как только счетчик
// доходит до нуля, с ожидающего потока снимается блокировка.
class CountDownLatchDemo {

    public static void main(String[] args) {
        // задаем количество потоков
        final int THREADS_COUNT = 6;
        // задаем значение счетчика
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        System.out.println("Начинаем");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    // считаем, что выполнение задачи занимает ~1 сек
                    Thread.sleep(500 + (int) (1500 * Math.random()));
                    cdl.countDown();
                    // как только задача выполнена, уменьшаем счетчик
                    System.out.println("Поток #" + w + " - end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            cdl.await();
            // пока счетчик не приравняется нулю, будем стоять на этой строке
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // как только все потоки выполнили свои задачи - пишем сообщение
        System.out.println("Работа завершена");
    }

}
