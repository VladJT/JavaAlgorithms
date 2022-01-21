package main.java.com.company.sort;

/**
 * метод быстрой сортировки
 */
public class QuickSort {

    public static int countIteration = 0;

    /**
     * метод быстрой сортировки
     */
    public static int sort(int[] array) {
        doSort(array, 0, array.length - 1);
        return countIteration;
    }

    /**
     * метод быстрой сортировки
     *
     * @param leftBorder  - левая граница массива
     * @param rightBorder - правая граница массива
     */
    public static void doSort(int[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = array[(leftMarker + rightMarker) / 2];
        do {
            countIteration++;
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (array[leftMarker] < pivot) {
                leftMarker++;
            }

            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (array[rightMarker] > pivot) {
                rightMarker--;
            }

            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    swap(array, leftMarker, rightMarker);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }

        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            doSort(array, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            doSort(array, leftBorder, rightMarker);
        }

    }

    public static void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
