package com.company.core.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class StreetRaceExample {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
    public static int placeResult = 1;

    public static void main(String[] args) {
        String names[] = {"🛺 #1", "🛸 #2", "🪂 #3", "🛻 #4"};
        System.out.println("☠️ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), names[i]);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            cdlStart.await();
            System.out.println("🏴‍☠️ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            cdlFinish.await();
            System.out.println("🏴‍☠️ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Car implements Runnable {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(StreetRaceExample.CARS_COUNT);
    private static final Lock lockFinishRace = new ReentrantLock();

    private long startTime;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, String name) {
        this.race = race;
        this.speed = speed;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится. speed = " + speed);
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();//Все участники должны стартовать одновременно, несмотря на разное время подготовки
            startTime = System.currentTimeMillis();
            StreetRaceExample.cdlStart.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        try {
            lockFinishRace.lock();
            if (StreetRaceExample.placeResult == 1) {
                System.out.println(this.name + " - WIN 👑");
            }
            System.out.println(this.name + " занял " + StreetRaceExample.placeResult++ + " место. Время в пути (милисек.) = " + (System.currentTimeMillis() - startTime));
        } finally {
            lockFinishRace.unlock();
        }
        StreetRaceExample.cdlFinish.countDown();
    }
}

abstract class Stage {
    protected int length;
    protected String description;

    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    private static final Semaphore tunnelSemaphore = new Semaphore(StreetRaceExample.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "[🗝️] Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится заехать в: " +
                        description);

                long time = System.currentTimeMillis();
                // В тоннель не может одновременно заехать больше половины участников (условность).
                tunnelSemaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description + ". Ожидание составило (милисек.) = " + (System.currentTimeMillis() - time));
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        description);
                tunnelSemaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}