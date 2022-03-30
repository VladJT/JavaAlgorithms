package com.company.core.oop.lesson07;

import java.util.Random;

class Main {
    public static void main(String[] args) {
        int size = 6;
        String[] names = {"Барсик", "Мурзик", "Оззи", "Стасик", "Валли", "Рыжик"};
        Cat[] cat = new Cat[size];
        for (int i = 0; i < size; i++) {
            cat[i] = new Cat(names[i], new Random().nextInt(4) + 3);//3-6
        }

        Plate plate = new Plate(10);
        plate.printInfo();
        for (int i = 0; i < size; i++) {
            cat[i].eat(plate);
            plate.printInfo();
            System.out.println("--------");
            if (plate.isEmpty()) {
                System.out.println("Наполняем миску заново");
                plate.fill();
                plate.printInfo();
                System.out.println("--------");
                if (cat[i].isHungry()) i--;//заново кормим,если кот не полностью сытый
            }
        }

    }
}
