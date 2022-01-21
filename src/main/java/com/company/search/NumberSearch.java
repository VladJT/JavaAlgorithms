package main.java.com.company.search;

import java.util.Arrays;

public class NumberSearch {

    public int iterationCount = 0;

    public int search(int[] arr) {
        int base;
        int start = 0;
        int end = arr.length - 1;

        // Частные случаи (чтобы зря не гонять поиск)
        // 1. массив длиной = 0 или не начинается с 1
        if (arr.length == 0 || arr[0] != 1) return 1;

        // 2. пропущенный элемент - последний (в массиве нет пропусков)
        if (arr[end] == end + 1) return arr[end] + 1;

        // Во всех остальных случаях ищем бинарным поиском
        while (end >= start) {
            iterationCount++;
            base = (start + end) / 2;

            // искомый элемент справа
            if (arr[base] == base + 1) {
                if (arr[base] == arr[base + 1] - 2) return arr[base] + 1;
                start = base + 1;
            }
            // искомый элемент слева
            else if (arr[base] == base + 2) {
                if (arr[base] == arr[base - 1] + 2) return arr[base] - 1;
                end = base - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 4, 5, 6, 7, 8};
        int[] a2 = {2};
        int[] a3 = {1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        NumberSearch s1 = new NumberSearch();
        NumberSearch s2 = new NumberSearch();
        NumberSearch s3 = new NumberSearch();

        System.out.println(Arrays.toString(a1) + " не хватает элемента: " + s1.search(a1) + ". Итераций: " + s1.iterationCount);
        System.out.println(Arrays.toString(a2) + " не хватает элемента: " + s2.search(a2) + ". Итераций: " + s2.iterationCount);
        System.out.println(Arrays.toString(a3) + " не хватает элемента: " + s3.search(a3) + ". Итераций: " + s3.iterationCount);
    }
}
