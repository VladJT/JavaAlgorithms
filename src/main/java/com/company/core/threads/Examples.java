package com.company.core.threads;

import java.util.*;
import java.util.concurrent.*;

// поток через функциональный интерфейс
class MyRunableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
            System.out.print(i + " [" + this.hashCode() + "], ");
    }
}

// поток через наследование от Thread
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++)
            System.out.println(i + " [" + this.getName() + "], ");
    }
}

//В большинстве случаев создавать подкласс, порожденный от
//класса Thread, следует в случае, если требуется дополнить его новыми функциями. Так, если
//переопределять любые другие методы из класса Thread не нужно, то можно ограничиться только
//реализацией интерфейса Runnable. Кроме того, реализация интерфейса Runnable позволяет
//создаваемому потоку наследовать класс, отличающийся от Thread.


class Examples {
    public static void main(String[] args) {
//        new Thread(new MyRunableClass()).start();
//        new Thread(new MyRunableClass()).start();
//        MyThread t1 = new MyThread();
//        MyThread t2 = new MyThread();
//        t1.setPriority(1);//min priority
//        t2.setPriority(10);//max priority (default = 5)
//        t1.setName("t1");
//        t2.setName("t2");
//        t1.start();
//        t2.start();
//        try {
//            t1.join();//основной поток ждет завершения данного thread
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//

        // testExecutor();
        //  testScheduledExecutorService2();
        testCollection();
        System.out.println("- THE END -");
        //   t.setDaemon(true);// заканчивает работу,когда завершаются остальные потоки

    }

    private static void testCollection() {
        Map<String, String> oldMap = new Hashtable<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> chm = new ConcurrentHashMap<>();


    }

    private static void testScheduledExecutorService() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> {
            System.out.println("Start " + new Date());
            try {
                Thread.sleep(new Random().nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End " + new Date());
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    private static void testScheduledExecutorService2() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleWithFixedDelay(() -> {
            System.out.println("Start " + new Date());
            try {
                Thread.sleep(new Random().nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End " + new Date());
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    private static void testExecutor() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 20; i++) {
            int k = i;
            pool.execute(() -> System.out.println(k + " - " + Thread.currentThread().getName()));
        }
        pool.shutdown();
    }


}
