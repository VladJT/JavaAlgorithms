package com.company.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Exceptions {

    public static void main(String[] args) {
        // testTryWithRes();
        // testReadFile("input","txt");

        try {
            testReadFile2("input", "txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("КАТАСТРОФА");
        }

        findNoOddNumbers();
    }

    static class NotOddNumberException extends ArithmeticException {
        public NotOddNumberException(int index, int num) {
            super("ЧИСЛО " + num + " С ИНДЕКСОМ " + index + " НЕ ЧЕТНОЕ! ");
        }
    }

    private static void findNoOddNumbers() {
        int[] arr = {6, 3, 2, 8, 4, 12, 11};
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 != 0) throw new NotOddNumberException(i, arr[i]);
            } catch (NotOddNumberException e) {
                count++;
                e.printStackTrace();
            }
        }
        System.out.println("Нечетных чисел: " + count);
    }

    // делегируем обработку исключения наверх (THROWS...)
    private static void testReadFile2(String fileName, String extFile) throws FileNotFoundException {
        File f = new File("src/main/resources/" + fileName + "." + extFile);
        Scanner s = null;
        s = new Scanner(f);
        System.out.println(s.nextLine());
    }

    // обрабатываем исключение сразу в методе
    private static void testReadFile(String fileName, String extFile) {
        File f = new File("src/main/resources/" + fileName + "." + extFile);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Очень жаль");
            System.exit(1);
        }
        System.out.println(s.nextLine());
    }

    // try with resources (только если реализован интерфейс Closeable). В Scanner - есть!
    private static void testTryWithRes() {
        try (Scanner s = new Scanner(System.in)) {
            int a = s.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("------------");
        }
    }
}