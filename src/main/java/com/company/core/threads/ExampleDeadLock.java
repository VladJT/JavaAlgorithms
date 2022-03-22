package com.company.core.threads;

class ExampleDeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();
        threadOne.start();
        threadTwo.start();
    }

    private static class ThreadOne extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1 захватил Lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 ожидает Lock2");
                synchronized (lock2) {
                    System.out.println("Thread1 захватил Lock1 и Lock2");
                }
            }
        }
    }

    private static class ThreadTwo extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread2 захватил Lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 ожидает Lock1");
                synchronized (lock1) {
                    System.out.println("Thread2 захватил Lock1 и Lock2");
                }
            }
        }
    }
}
