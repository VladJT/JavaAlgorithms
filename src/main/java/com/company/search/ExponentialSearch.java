package com.company.search;

import java.util.Objects;

class ExponentialSearch {

    public static int search(int[] arr, int num2Find) {
        int i = 0;

        if (Objects.equals(arr[0], num2Find)) {
            return 0;
        }
        if (Objects.equals(arr[arr.length - 1], num2Find)) {
            return arr.length - 1;
        }

        int base = 1;
        while (base < arr.length && arr[base] < num2Find) {
            if (Objects.equals(arr[0], num2Find)) return base;
            i++;
            base *= 2;
        }

        System.out.println("Экспоненциальный поиск. Итераций: " + i);
        return BinarySearch.search(arr, num2Find, base / 2 + 1, Math.min(base - 1, arr.length - 1));
    }
}
