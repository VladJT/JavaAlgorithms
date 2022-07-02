package com.company.core.threads;

//В примере два потока синхронизируют свою работу с помощью методов wait() и
//notify(). Один поток отвечает за печать буквы А, второй – B.
//При запуске невозможно предсказать какой из потоков начнет выполнение первым. Если первый
//запускается поток B, то он просто переходит в режим ожидания. После чего поток А производит печать
//буквы в консоль и будит поток B. Далее за счет механизма wait()/notify() эти потоки работают
//последовательно и печать гарантированно начинается с буквы А.
class WaitNotifyClass {
    private volatile char currentLetter = 'A';


    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        t1.start();
        t2.start();
    }


    public synchronized void printA() {
        try {
            for (int i = 0; i < 10; i++) {
                if (currentLetter != 'A') {
                    wait();
                }
                System.out.print("A");
                Thread.sleep(100);
                currentLetter = 'B';
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void printB() {
        try {
            for (int i = 0; i < 10; i++) {
                if (currentLetter != 'B') {
                    wait();
                }
                System.out.print("B");
                Thread.sleep(100);
                currentLetter = 'A';
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


