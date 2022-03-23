package com.company.core.threads;

class TestSync {
    static Object mon = new Object();
    static int counter = 0;
    static int steps = 10000;
    static int s = 0;

    public static void main(String[] args) throws InterruptedException {
        TestSync ts = new TestSync();
        Thread t1 = new Thread(ts::incCounter);
        Thread t2 = new Thread(ts::decCounter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("--END-- " + counter + " - " + s);
    }

    public void incCounter() {
        synchronized (mon) {
            for (int i = 0; i < steps; i++) {
                counter++;
                s++;

            }
        }
    }

    public void decCounter() {
        synchronized (mon) {
            for (int i = 0; i < steps; i++) {
                counter--;
                s++;

            }
        }
    }

}
