package com.company.search;

class InterpolationSearch {

    public static int search(int[] arr, int num2Find) {
        int start = 0;
        int end = arr.length - 1;
        int base;
        int i = 0;
        while (end >= start && num2Find >= arr[start] && num2Find <= arr[end]) {
            if (arr[start] == arr[end]) break;
            i++;
            base = (int) (start + 1.0 * (end - start) / (arr[end] - arr[start]) * (num2Find - arr[start]));
            if (arr[base] == num2Find) {
                System.out.println("Найдено. Итераций: " + i);
                return base;
            } else if (arr[base] < num2Find) {
                start = base + 1;
            } else {
                end = base - 1;
            }

        }

        if (arr[start] == num2Find) return start;
        System.out.println("НЕ найдено. Итераций: " + i);
        return -1;
    }
}
