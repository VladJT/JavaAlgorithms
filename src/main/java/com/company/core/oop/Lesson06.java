package com.company.core.oop;

import java.util.Random;

// паттерн factory method
interface IAnimal{
    public void run(int distance);
    public void swim(int distance);
}

abstract class AnimalCreator{
    public abstract IAnimal factoryMethod(String name);
}

class CatCreator extends AnimalCreator{
    @Override
    public IAnimal factoryMethod(String name) {
        return new Cat("Кошка",  name, 200, 0);
    }
}

class DogCreator extends AnimalCreator{
    @Override
    public IAnimal factoryMethod(String name) {
        return new Dog("Собака",  name, 500, 10);
    }
}


abstract class Animal {
    private final int limitRun; // максимальная дистанция бега
    private final int limitSwim;// максимальная дистанция плавания
    private final String name;// кличка животного
    private final String type;//


    Animal(String type, String name, int limitRun, int limitSwim) {
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
    Dog(String animalType, String name, int limitRun, int limitSwim) {
        super(animalType, name, limitRun, limitSwim);
    }
}

class Cat extends Animal implements IAnimal {

    Cat(String type, String name, int limitRun, int limitSwim) {
        super(type, name, limitRun, limitSwim);
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


