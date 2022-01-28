package com.company.recursion;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Написать программу «Задача о рюкзаке» с помощью рекурсии.
 */
public class Bag {

    /**
     * ПРЕДМЕТЫ
     */
    private class Item {
        String name;
        double weight;
        int price;

        Item(String _n, double _w, int _p) {
            name = _n;
            weight = _w;
            price = _p;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getInfo() {
            return name + " вес " + weight + " цена " + price;
        }
    }

    // максимальный размер рюкзака
    private int MAX_BAG_SIZE;

    // СПИСОК ВСЕХ ПРЕДМЕТОВ
    List items = new ArrayList<Item>();

    // ВСЕ КОМБИНАЦИИ
    Set<List<Item>> result = new LinkedHashSet<>();

    // ЛУЧШИЙ ВЫБОР
    List bestItems = new ArrayList<Item>();
    int bestPrice = 0;
    double bestWeight = 0;

    private void findBestCombination() {
        result.clear();
        result.add(items);
        find(items);
        // выбираем лучший вариант из полученных комбинаций
        for (List<Item> l : result) {
            checkBestPrice(l);
        }
    }

    private void find(List<Item> l) {
        // базовый случай
        if (l.size() == 1) {
            return;
        }
        //рекурсивный случай
        for (int i = 0; i < l.size(); i++) {
            List newList = new ArrayList<Item>();
            newList.addAll(l);
            // убираем 1 предмет из сумки и дополняем список возможных комбинаций
            newList.remove(i);
            result.add(newList);
            find(newList);
        }
    }

    private void checkBestPrice(List<Item> newList) {
        int price = 0;
        double weight = 0;
        for (Item i : newList) {
            price += i.price;
            weight += i.weight;
        }
        if (price > bestPrice && weight <= MAX_BAG_SIZE) {
            bestPrice = price;
            bestWeight = weight;
            bestItems.clear();
            bestItems.addAll(newList);
        }
    }


    public void full() {
        MAX_BAG_SIZE = 5;

        System.out.println("ВСЕ ПРЕДМЕТЫ:");
        items.add(new Item("Телефон", 1, 8000));
        items.add(new Item("Ноутбук", 3, 7500));
        items.add(new Item("Утюг", 2, 3000));
        items.add(new Item("ЛЕГО", 1.5, 4000));
        items.add(new Item("Часы", 0.5, 4000));
        System.out.println(items + "\n");

        findBestCombination();
        System.out.println("-- Комбинации для анализа [" + result.size() + "]--");
        System.out.println(result + "\n");


        System.out.println("---Оптимальный выбор для сумки " + MAX_BAG_SIZE + " " + bestItems + " ---\nИтоговая стоимость: " + bestPrice + "\nВес: " + bestWeight);
        for (Item i : (List<Item>) bestItems) {
            System.out.println(i.getInfo());
        }

    }


    public static void main(String[] args) {
        Bag bag = new Bag();
        bag.full();
    }

}
