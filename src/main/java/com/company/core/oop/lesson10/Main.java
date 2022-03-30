package com.company.core.oop.lesson10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class Main {
    public static void main(String[] args) {
        Integer[] arrInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(arrInt));
        swapArrayElements(arrInt, 0, 7);
        System.out.println(Arrays.toString(arrInt));

        String[] arrString = {"apple", "cola", "banana", "juice", "strawberry"};
        System.out.println(Arrays.toString(arrString));
        swapArrayElements(arrString, 0, 4);
        System.out.println(Arrays.toString(arrString));

        System.out.println("-----");

        List<String> list = arrayToList(arrString);
        System.out.println(list.getClass().getName() + " " + list);

    }

    // 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
    static <T> void swapArrayElements(T[] arr, int index1, int index2) {
        if (index1 < 0 || index1 >= arr.length || index2 < 0 || index2 >= arr.length)
            throw new IllegalArgumentException();
        T temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }


    // 2. Написать метод, который преобразует массив в ArrayList
    static <T> ArrayList<T> arrayToList(T[] arr) {
        return Arrays.stream(arr).collect(Collectors.toCollection(ArrayList::new));
    }

}
