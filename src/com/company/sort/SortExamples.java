package com.company.sort;

import com.company.sort.*;


public class SortExamples {


    public static void main(String[] args) {
        int size = 100;
        MyArray a1 = new MyArray(size);
        a1.initArray(1000);
        a1.printArray();
        int c = BubbleSort.sort(a1.array);
        a1.printArray();
        System.out.println("Пузырьковая сортировка. Итераций = " + c);
        System.out.println();

        MyArray a2 = new MyArray(size);
        a2.initArray(1000);
        a2.printArray();
        c =  SelectionSort.sort(a2.array);
        a2.printArray();
        System.out.println("Сортировка выбором. Итераций = " + c);
        System.out.println();

        MyArray a3 = new MyArray(size);
        a3.initArray(1000);
        a3.printArray();
        c = QuickSort.sort(a3.array);
        a3.printArray();
        System.out.println("Быстрая сортировка. Итераций = " + c);
        System.out.println();

        MyArray a4 = new MyArray(size);
        a4.initArray(1000);
        a4.printArray();
        c =  InsertionSort.sort(a4.array);
        a4.printArray();
        System.out.println("Сортировка вставками. Итераций = " + c);
        System.out.println();
    }


}
