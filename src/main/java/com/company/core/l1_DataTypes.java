package com.company.core;

class l1_DataTypes {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

        System.out.println("""
                Много
                строчный
                текст
                """);
    }


    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }


    public static void checkSumSign() {
        int a = 10;
        int b = -2;
        int sum = a + b;

        if (sum >= 0) System.out.println("Сумма положительная");
        else System.out.println("Сумма отрицательная");
    }


    public static void printColor() {
        int value = 101;
        if (value <= 0)
            System.out.println("Красный");
        else if (value > 0 && value <= 100)
            System.out.println("Желтый");
        else System.out.println("Зеленый");
    }


    public static void compareNumbers() {
        int a = 10;
        int b = 8;

        if (a >= b) System.out.println("a >= b");
        else System.out.println("a < b");
    }

}

