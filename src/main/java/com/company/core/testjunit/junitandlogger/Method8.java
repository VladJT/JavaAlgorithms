package com.company.core.testjunit.junitandlogger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

class Method8 {
    public static void main(String[] args) {
        new Method8().run();
    }

    Logger logger;

    private void run() {
        PropertyConfigurator.configure("src/main/resources/logs/configs/log4j.properties");
        logger = Logger.getLogger("method8_logger");

        int[] intArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1};
        System.out.println(Arrays.toString(intArray));
        rotateArray(intArray, 14);
        rotateArray(intArray, -13);
    }

    public int[] rotateArray(int[] intArray, int step) {
        int[] arr = intArray.clone();
        int stepsCount = Math.abs(step);
        // уменьшаем сложность алгоритма
        if (stepsCount > arr.length) stepsCount = stepsCount % arr.length;

        for (int i = 0; i < stepsCount; i++) {
            if (step > 0) rotateRight(arr);
            else rotateLeft(arr);
        }

        String logString = String.format("Исходный массив: %s, смещение: %s, результат: %s", Arrays.toString(intArray), step, Arrays.toString(arr));
        logger.info(logString);
        return arr;
    }

    private void rotateRight(int[] arr) {
        int tmp = arr[arr.length - 1];
        System.arraycopy(arr, 0, arr, 1, arr.length - 1);
        arr[0] = tmp;
    }

    private void rotateLeft(int[] arr) {
        int tmp = arr[0];
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        arr[arr.length - 1] = tmp;
    }

}