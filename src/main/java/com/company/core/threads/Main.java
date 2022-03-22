package com.company.core.threads;


class Main {
    static Object mon = new Object();
    static Object mon2 = new Object();

    static class MyThread extends Thread {
        String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            printX(name);
        }
    }

    static String[] threads = {"Thread 1", "Thread 2", "Thread 3", "Thread 4"};
    static String curThread = threads[0];
    static int curIndex = 0;
    static boolean needMon = true;
    static Thread tm;

    public static void main(String[] args) {
        MyThread[] t = new MyThread[threads.length];

        tm = new Thread(() -> {
            while (t[threads.length - 1].getState() != Thread.State.TERMINATED) {
                if (needMon) {
                    synchronized (mon2) {
                        needMon = false;
                        for (int i = 0; i < threads.length; i++) {
                            System.out.print(threads[i] + " - " + t[i].getState() + "; ");
                        }
                        System.out.println("\n-----");
                    }
                }
            }
        });

        for (int i = 0; i < threads.length; i++) {
            t[i] = new MyThread(threads[i]);
            t[i].start();
        }
        tm.start();
    }

    public static void printX(String nameThread) {
        synchronized (mon) {
            try {
                for (int i = 1; i <= 5; i++) {

                    while (!curThread.equals(nameThread)) {
                        mon.wait();
                    }
                    synchronized (mon2) {
                        System.out.println(i + " [" + nameThread + "], ");
                        needMon = true;
                    }
                    Thread.sleep(100);

                    curIndex++;
                    if (curIndex > threads.length - 1) curIndex = 0;
                    curThread = threads[curIndex];
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
