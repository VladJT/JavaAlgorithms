package com.company.core.threads;

//Синхронизированный метод создается путем указания ключевого слова synchronized в его
//объявлении. Как только синхронизированный метод любого объекта получает управление, объект
//блокируется, и ни один синхронизированный метод этого объекта не может быть вызван другим
//потоком.
//Потоки, которым требуется синхронизированный метод, используемый другим потоком, ожидают до
//тех пор, пока не будет разблокирован объект, для которого он вызывается. Когда синхронизированный
//метод завершается, объект, для которого он вызывался, разблокировался.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Пример №1
// При указании ключевого слова synchronized в объявлении метода, в роли монитора выступает объект,
// у которого был вызван синхронизированный метод. То есть в примере 1 два потокане смогут параллельно выполнять method1() и method2().
class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
    }

    public synchronized void method1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-M1-");
    }

    public synchronized void method2() {
        System.out.println("M2");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-M2-");
    }
}

// Пример №2
//Второй способ синхронизации основан на использовании отдельных объектов типа java.lang.Object в качестве мониторов
// В этом случае в роли монитора выступает объект lock1, соответственно два потока смогут
//параллельно выполнять первую часть метода method1(), однако в блок синхронизации в единицу
//времени может зайти только один поток, так как происходит захват монитора lock1
class Example_SB_2 {
    private Object lock1 = new Object();

    public static void main(String[] args) {
        Example_SB_2 e2 = new Example_SB_2();
        System.out.println("Старт main потока");
        new Thread(() -> e2.method()).start();
        new Thread(() -> e2.method()).start();
    }

    public void method() {
        System.out.println("Метод запущен");
        System.out.println("Блок 1 начало");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-Блок 1 конец-");

        synchronized (lock1) {
            System.out.println("Начало синхронизированного блока");
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("-Конец синхронизированного блока-");
        }
        System.out.println("Метод завершил свою работу");
    }
}


// Пример №3
// При третьем способе синхронизации, в роли монитора может выступать сам класс.
// При указании ключевого слова synchronized в объявлении статического метода в роли монитора
// выступает сам класс.
class Example_SB_3 {
    public static void main(String[] args) {
        System.out.println("Start");
        new Thread(() -> method()).start();
        new Thread(() -> method()).start();
    }

    public synchronized static void method() {
        System.out.println("start");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--end--");
    }
}


// Пример №4
// Интерфейс Lock из пакета java.util.concurrent – это продвинутый механизм синхронизации потоков.
// По гибкости он выигрывает в сравнении с блоками синхронизации.
// Основные отличия между Lock и синхронизированными блоками:
//● Синхронизированные блоки не гарантируют, что сохранится порядок обращения потоков к
//критической секции;
//● Нельзя выйти из синхронизированного блока по времени ожидания (timeout);
//● Синхронизированные блоки должны полностью содержаться в одном методе. Lock может
//быть захвачен в одном методе, а освобожден в другом.
class Example_SB_4 {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Example_SB_4 e2 = new Example_SB_4();
        System.out.println("Старт main потока");
        Thread t1 = new Thread(() -> e2.method());
        t1.setName("t1");
        Thread t2 = new Thread(() -> e2.method());
        t2.setName("t2");
        Thread t3 = new Thread(() -> e2.method());
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }

    //Создаем объект типа Lock и вызываем у него метод lock() – он захватывается. Если другой поток
    //попытается вызвать этот метод у того же объекта – он будет блокирован до тех пор, пока поток,
    //удерживающий объект lock, не освободит его через метод unlock(). Тогда этот объект смогут
    //захватить другие потоки.
    public void method() {
        System.out.println(" [" + Thread.currentThread().getName() + "] start");
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(i + " [" + Thread.currentThread().getName() + "]");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println(" [" + Thread.currentThread().getName() + "] finish");
    }
}


class ExamplesSync {
}
