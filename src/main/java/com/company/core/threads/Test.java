package com.company.core.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static class CheckReentrantLock {
        private List<String> list = new ArrayList<>();
        private Lock lock;
        public CheckReentrantLock(boolean fair) {
            lock = new ReentrantLock(fair);
        }
        public void addToList(String str) {
            try {
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    list.add(str);
                    System.out.println("Added " + str);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void removeFirstFromList() {
            try {
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.print("Try to remove. ");
                    if (list.size() > 0) {
                        System.out.print("Removed " + list.get(0));
                        list.remove(0);
                    }
                    System.out.println();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CheckReentrantLock checkNonFairLock = new CheckReentrantLock(false);
        CheckReentrantLock checkFairLock = new CheckReentrantLock(true);
        System.out.println("Not fair unlock");
        RunLock(checkNonFairLock);
        System.out.println("Fair unlock");
        RunLock(checkFairLock);
    }

    private static void RunLock(CheckReentrantLock checkLock) {
        Thread adder = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                checkLock.addToList(Integer.toString(i));
            }
        });
        Thread remover = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                checkLock.removeFirstFromList();
            }
        });
        adder.start();
        remover.start();
        try {
            adder.join();
            remover.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
