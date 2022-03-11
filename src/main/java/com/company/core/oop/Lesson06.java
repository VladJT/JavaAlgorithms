package com.company.core.oop;

import java.util.Random;

// паттерн factory method
interface IAnimal {
    void run(int distance);

    void swim(int distance);

    default void eat(){
        System.out.println("Животное поело");
    }
}

abstract class AnimalCreator {
    public abstract IAnimal factoryMethod(String name);
}

class CatCreator extends AnimalCreator {
    @Override
    public IAnimal factoryMethod(String name) {
        return new Cat("Кошка", name, 200, new NoSwimStrategy());
    }
}

class DogCreator extends AnimalCreator {
    @Override
    public IAnimal factoryMethod(String name) {
        return new Dog("Собака", name, 500, new BasicSwimStategy(10));
    }
}

interface SwimStrategy {
    void swim(int distance);
}

class NoSwimStrategy implements SwimStrategy {
    @Override
    public void swim(int distance) {
        System.out.println("не умеет плавать");
    }
}

class BasicSwimStategy implements SwimStrategy {
    private final int limitSwim;// максимальная дистанция плавания

    public BasicSwimStategy(int limitSwim) {
        this.limitSwim = limitSwim;
    }

    @Override
    public void swim(int distance) {
        String st = ((distance <= limitSwim) ? "проплыл " + distance + " м" : "не может проплыть " + distance + " м");
        System.out.println(st);
    }
}


abstract class Animal {
    private final int limitRun; // максимальная дистанция бега
    private final String name;// кличка животного
    private final String type;// тип животного (Кошка, Собака)

    SwimStrategy swimStrategy;

    Animal(String type, String name, int limitRun, SwimStrategy swimStrategy) {
        this.type = type;
        this.name = name;
        this.limitRun = limitRun;
        this.swimStrategy = swimStrategy;
    }

    public void run(int distance) {
        String st = "";
        if (limitRun == 0)
            st = type + " не умеет бегать";
        else
            st = this + ((distance <= limitRun) ? " пробежал " + distance + " м" : " не может пробежать " + distance + " м");
        System.out.println(st);
    }

    public void swim(int distance) {
        swimStrategy.swim(distance);
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

//    static class SimpleFactory {
//        public static Animal createAnimal(AnimalType animalType, String name) {
//            Animal newAnimal = null;
//            switch (animalType) {
//                case КОШКА -> newAnimal = new Cat(animalType, name);
//                case СОБАКА -> newAnimal = new Dog(animalType, name);
//            }
//            return newAnimal;
//        }
//    }//..... SimpleFactory ......
}


class Dog extends Animal implements IAnimal {
    Dog(String animalType, String name, int limitRun, SwimStrategy s) {
        super(animalType, name, limitRun, s);
    }
}

class Cat extends Animal implements IAnimal {

    Cat(String type, String name, int limitRun, SwimStrategy s) {
        super(type, name, limitRun, s);
    }
}


class Lesson06 {
    public static void main(String[] args) {
        AnimalCreator[] creators = {new DogCreator(), new CatCreator()};

        int size = 5;
        IAnimal[] animals = new IAnimal[size];
        String[] names = {"Барсик", "Мурзик", "Полкан", "Озя", "Ричард"};
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            //animals[i] = (random.nextInt(2) == 0) ? Animal.SimpleFactory.createAnimal(AnimalType.КОШКА, names[i]) : Animal.SimpleFactory.createAnimal(AnimalType.СОБАКА, names[i]);
            animals[i] = creators[random.nextInt(2)].factoryMethod(names[i]);
        }

        int countCats = 0, countDogs = 0;

        for (IAnimal a : animals) {
            int randomSwimDistance = random.nextInt(15) + 1;//1-15
            int randomRunDistance = random.nextInt(501) + 100;//100-600
            a.run(randomRunDistance);
            a.swim(randomSwimDistance);
            System.out.println("----------");

            if (a instanceof Cat)
                countCats++;
            if (a instanceof Dog)
                countDogs++;
        }

        System.out.println("У нас " + countDogs + " собак и " + countCats + " кошек");
    }
}


