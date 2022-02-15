package com.company.sort_objects;

import java.util.HashMap;
import java.util.Random;


/**
 * 1. Отсортировать массив, состоящий из экземпляров класса Notebook в количестве 10000 штук.
 * Условия для сортировки:
 * 1) по цене. От 500 до 2000 долларов с шагом в 50
 * если цена равная, то
 * 2) по кол-ву оперативной памяти (от 4 до 24 с шагом 4)
 * если памяти тоже равное количество, то
 * 3) по производителю:
 * Lenuvo > Asos > MacNote > Eser > Xamiou
 */
// 1
class Notebook {

    public static HashMap<Integer, String> vendors;

    private int price;
    private int ram;
    private int keyName;
    private int index;

    public Notebook(int _index) {
        Random r = new Random();
        price = (r.nextInt(31) + 10) * 50;
        ram = (r.nextInt(6) + 1) * 4;
        keyName = r.nextInt(vendors.size()) + 1;
        index = _index;
    }

    public void print() {
        System.out.println(price + "$ - " + ram + " - " + vendors.get(keyName) + " (id: " + index + ")");
    }

    public boolean isHigherThan(Notebook y) {
        boolean result = false;

        // 1. Сравнение по цене
        if (this.price > y.price) result = true;
        else if (this.price < y.price) result = false;
        else {
            // 2. Сравнение по памяти
            if (this.ram > y.ram) result = true;
            else if (this.ram < y.ram) result = false;
            else {
                // 3. сравнение по марке
                if (this.keyName < y.keyName) result = true;
                else result = false;
            }
        }

        return result;
    }


}
