package com.company.sort_objects;

import java.util.HashMap;
import com.company.sort.*;


public class Main {

    public static void main(String[] args) {
        Notebook.vendors = new HashMap<>();
        Notebook.vendors.put(1, "Lenuvo");
        Notebook.vendors.put(2, "Asos");
        Notebook.vendors.put(3, "MacNote");
        Notebook.vendors.put(4, "Eser");
        Notebook.vendors.put(5, "Xamiou");

        int amount = 10000;
        Notebook[] notes = new Notebook[amount];

        for (int i = 0; i < notes.length; i++) {
            notes[i] = new Notebook(i);
        }
        System.out.println("Инициализирован массив ноутбуков в количестве: " + amount);

        SpeedTest.startTime();
        int countIteration = InsertionSort.sort(notes);

        for (int i = 0; i < notes.length; i++) {
            notes[i].print();
        }

        SpeedTest.endTime();
        System.out.println("Сортировка вставками по возрастанию закончена. Количество итераций: " + countIteration);
    }
}
