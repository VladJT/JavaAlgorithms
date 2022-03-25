package com.company.core.threads;

import java.util.concurrent.Semaphore;

// Semaphore ограничивает количество потоков при работе с ресурсами. Для этого служит счетчик. Если
// его значение больше нуля, то потоку разрешается доступ, а значение уменьшается. Если счетчик
// равен нулю, то текущий поток блокируется до освобождения ресурса. Для получения доступа
// используется метод acquire(), для освобождения – release()
class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore smp = new Semaphore(2);
        //С помощью метода acquire() одновременно захватить семафор могут только два потока. Остальные
        //становятся в очередь, пока один из «захватчиков» не освободит семафор методом release().

        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " перед семафором");
                    smp.acquire();//Для получения доступа используется метод acquire(), для освобождения – release()
                    System.out.println("Поток " + w + " получил доступ к ресурсу");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток " + w + " освободил ресурс");
                    smp.release();
                }
            }).start();
        }
    }
}

