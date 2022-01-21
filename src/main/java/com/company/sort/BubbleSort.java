package main.java.com.company.sort;

/**
 * сортировка "пузырьком"
 * O(n^2)
 */
public class BubbleSort {
    public static int sort(int[] array) {
        int countIteration = 0;
        boolean needSort = true;
        int n = array.length;
        while (needSort) {
            needSort = false;
            for (int i = 1; i < n; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    needSort = true;
                    countIteration++;
                }
            }
            n--;
        }
        return countIteration;
    }

    public static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
