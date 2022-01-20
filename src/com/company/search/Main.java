package com.company.search;

import com.company.common.MyArray;
import com.company.sort.QuickSort;

public class Main {

    public static void main(String[] args) {

        MyArray arr = new MyArray(100);
        arr.initArray(100);
        int c = QuickSort.sort(arr.getArray());
        arr.printArray();

        int num2Find = 11;
        System.out.println("Бинарный поиск. Номер элемента " +num2Find+ " в массиве: " + BinarySearch.search(arr.getArray(), num2Find));
        System.out.println("Экспоненциальный поиск. Номер элемента " +num2Find+ " в массиве: " + ExponentialSearch.search(arr.getArray(), num2Find));
    }
}
