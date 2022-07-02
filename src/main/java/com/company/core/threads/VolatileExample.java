package com.company.core.threads;

class VolatileExample {

    // volatile - запрет кеширования переменной
    static volatile int i;


    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> m1()).start();
        new Thread(() -> m2()).start();


    }

    static void m1() {
        int localVar = i;
        while (i < 5) {
            if (localVar != i) {
                localVar = i;
                System.out.println("t1 " + localVar);
            }
        }

    }

    static void m2() {
        int localVar = i;
        while (i < 5) {
            i = ++localVar;
            System.out.println("t2 " + localVar);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
