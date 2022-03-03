package com.company.core.lesson10;


import java.util.ArrayList;
import java.util.List;

class MyArray<T> {
    private T[] arr;

    public MyArray(T[] arr) {
        this.arr = arr;
    }

    public void printInfo() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    // Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
    public void swapElements(int index1, int index2) {
        if (index1 < 0 || index1 >= arr.length || index2 < 0 || index2 >= arr.length)
            throw new IllegalArgumentException();
        T temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }

}


class Main {
    public static void main(String[] args) {
        Integer[] arrInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MyArray<Integer> arr1 = new MyArray<>(arrInt);
        arr1.printInfo();
        arr1.swapElements(4, 8);
        arr1.printInfo();

        String[] arrString = {"apple", "cola", "banana", "juice", "strawberry"};
        MyArray<String> arr2 = new MyArray<>(arrString);
        arr2.printInfo();
        arr2.swapElements(0, 4);
        arr2.printInfo();

        System.out.println("-----");

        List<String> list = arrayToList(arrString);
        System.out.println(list.get(1));

    }

    // Написать метод, который преобразует массив в ArrayList
    private static <T> ArrayList<T> arrayToList(T[] arr) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

}
