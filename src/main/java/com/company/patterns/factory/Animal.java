package com.company.patterns.factory;


// Простая фабрика (Simple Factory)

/**
 * 5 шагов к открытию собственной фабрики
 * Шаг 1. У тебя в программе класс с несколькими потомками
 * Шаг 2. Ты создаешь enum, в котором определяешь enum-переменную для каждого класса-наследника:
 * Шаг 3. Ты строишь свою фабрику. Называешь её MyClassFactory
 * Шаг 4. Ты создаешь в своей фабрике метод createMyClass, который принимает в себя переменную-enum MyClassType.
 * Шаг 5. Ты пишешь в теле метода блок switch-case, в котором перебираешь все enum значения и создаешь экземпляр класса, соответствующий enum значению
 */

enum AnimalType {
    CAT,
    DOG,
    LION
}


class Animal {

    public void init1() {
        System.out.println("Расчесываем");
    }

    public void init2() {
        System.out.println("Моем");
    }

    public String getVoice() {
        return "? any animal voice";
    }


    static class AnimalSimpleFactory {
        public static Animal createAnimal(AnimalType animalType) {
            Animal a = null;
            switch (animalType) {
                case CAT -> a = new Cat();
                case DOG -> a = new Dog();
                case LION -> a = new Lion();
            }
            a.init1();
            a.init2();
            return a;
        }
    }//..... AnimalFactory ......
}

class Cat extends Animal {
    @Override
    public String getVoice() {
        return "Мяу!";
    }
}

class Lion extends Animal {
    @Override
    public String getVoice() {
        return "РРРАУУУ!";
    }
}

class Dog extends Animal {
    @Override
    public String getVoice() {
        return "ГАВ-ГАВ!";
    }
}

class Main {

    public static void main(String[] args) {
        Animal barsik = Animal.AnimalSimpleFactory.createAnimal(AnimalType.LION);
        System.out.println("Команда /голос/: " + barsik.getVoice());
    }
}