package main.java.com.company.search;

import main.java.com.company.common.*;
import main.java.com.company.sort.QuickSort;

public class Main {

    public static void main(String[] args) {

        MyArray arr = new MyArray(100);
        arr.initUniqueArray(2);
        // int c = QuickSort.sort(arr.getArray());
        arr.printArray();

        int num2Find = 10;
        System.out.println("Бинарный поиск. Число " + num2Find + " в массиве имеет индекс: " + BinarySearch.search(arr.getArray(), num2Find));
        System.out.println();
        System.out.println("Экспоненциальный поиск. Число " + num2Find + " в массиве имеет индекс: " + ExponentialSearch.search(arr.getArray(), num2Find));
        System.out.println();
        System.out.println("Интерполяционный поиск. Число " + num2Find + " в массиве имеет индекс: " + InterpolationSearch.search(arr.getArray(), num2Find));

    }
}
