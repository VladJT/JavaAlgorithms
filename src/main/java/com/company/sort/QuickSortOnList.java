package com.company.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuickSortOnList {

    public static List<Integer> quickSort(List<Integer> arr) {
        // базовый случай рекурсии
        if (arr.isEmpty()) return arr;

        Integer base = arr.get(0);
        List<Integer> left = new LinkedList<>();
        List<Integer> middle = new LinkedList<>();
        List<Integer> right = new LinkedList<>();

        for (Integer n : arr) {
            if (n.equals(base)) middle.add(base);
            else if (n.compareTo(base) > 0) right.add(n);
            else left.add(n);
        }

        // рекурсивный случай
        left = quickSort(left);
        right = quickSort(right);
        left.addAll(middle);
        left.addAll(right);
        return left;
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 6, 2, 6, 3, 1, 7, 5, 2, 5, 9};
        List<Integer> l = Arrays.asList(arr);

        System.out.println(QuickSortOnList.quickSort(l));
    }
}
