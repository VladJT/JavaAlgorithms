package com.company.sort;

/**
 * сортировка выбором
 * O(n^2)
 */
class SelectionSort {

    public static int sort(int[] array) {
        int countIteration = 0;
        int lastIndex = array.length - 1;

        for (int i = 0; i <= lastIndex; i++) {
            int minIndex = i;
            int maxIndex = i;
            for (int j = minIndex + 1; j < lastIndex; j++) {
                countIteration++;
                if (array[j] < array[minIndex])
                    minIndex = j;
                if (array[j] > array[maxIndex])
                    maxIndex = j;
            }
            swap(array, i, minIndex);
            swap(array, lastIndex, maxIndex);
            lastIndex--;
        }
        return countIteration;
    }


    public static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
