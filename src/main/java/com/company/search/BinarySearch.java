package com.company.search;

class BinarySearch {

    public static int search(int[] arr, int num2Find) {
        return search(arr, num2Find, 0, arr.length - 1);
    }

    public static int search(int[] arr, int num2Find, int start, int end) {
        int base;
        int i = 0;
        while (end >= start) {
            i++;
            base = (start + end) / 2;
            if (arr[base] == num2Find) {
                System.out.println("Найдено. Итераций: " + i);
                return base;
            } else if (arr[base] < num2Find) {
                start = base + 1;
            } else {
                end = base - 1;
            }

        }
        System.out.println("НЕ найдено. Итераций: " + i);
        return -1;
    }
}
