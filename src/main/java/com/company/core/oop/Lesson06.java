package com.company.core.oop;

import java.util.Random;


enum AnimalType {
    КОШКА,
    СОБАКА
}

abstract class Animal {
    private final int limitRun; // максимальная дистанция бега
    private final int limitSwim;// максимальная дистанция плавания
    private final AnimalType type;// тип животного
    private final String name;// кличка животного

    Animal(AnimalType type, String name, int limitRun, int limitSwim) {
        this.type = type;
        this.name = name;
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
    }

    public void run(int distance) {
        String st = "";
        if (limitRun == 0) st = type + " не умеет бегать";
        else
            st = this + ((distance <= limitRun) ? " пробежал " + distance + " м" : " не может пробежать " + distance + " м");
        System.out.println(st);
    }

    public void swim(int distance) {
        String st = "";
        if (limitSwim == 0) st = type + " не умеет плавать";
        else
            st = this + ((distance <= limitSwim) ? " проплыл " + distance + " м" : " не может проплыть " + distance + " м");
        System.out.println(st);
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    static class SimpleFactory {
        public static Animal createAnimal(AnimalType animalType, String name) {
            Animal newAnimal = null;
            switch (animalType) {
                case КОШКА -> newAnimal = new Cat(animalType, name);
                case СОБАКА -> newAnimal = new Dog(animalType, name);
            }
            return newAnimal;
        }
    }//..... SimpleFactory ......
}


class Dog extends Animal {
    Dog(AnimalType animalType, String name) {
        super(animalType, name, 500, 10);
    }
}

class Cat extends Animal {
    Cat(AnimalType animalType, String name) {
        super(animalType, name, 200, 0);
    }
}


class Lesson06 {
    public static void main(String[] args) {
        int size = 5;
        Animal[] animals = new Animal[size];
        String[] names = {"Барсик", "Мурзик", "Полкан", "Озя", "Ричард"};
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            animals[i] = (random.nextInt(2) == 0) ? Animal.SimpleFactory.createAnimal(AnimalType.КОШКА, names[i]) : Animal.SimpleFactory.createAnimal(AnimalType.СОБАКА, names[i]);
        }

        int countCats = 0, countDogs = 0;

        for (Animal a : animals) {
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


