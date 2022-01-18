package com.company.sort_objects;


public class InsertionSort {
    public static int sort(Notebook[] array) {
        int countIteration = 0;
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            Notebook value = array[i];

            while (j >= 0 && array[j].isHigherThan(value)) {
                array[j + 1] = array[j];
                j--;
                countIteration++;
            }
            array[j + 1] = value;
        }

        return countIteration;
    }
}
