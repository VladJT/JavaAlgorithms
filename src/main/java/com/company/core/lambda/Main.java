package com.company.core.lambda;


class Main {

    // функциональный интерфейс
    @FunctionalInterface
    interface Math {
        abstract int call(int x, int y);
    }


    // функция высшего порядка
    public static int mathFunc(Math operator, int a, int b) {
        return operator.call(a, b);
    }

    public static void main(String[] args) {

        // Анонимный класс
        Runnable obj = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                System.out.println(this.getClass().getName());
            }
        };
        obj.run();

        // lambda
        Runnable r1 = () -> {
            System.out.println("2");
        };
        r1.run();

        Math summa = (x, y) -> {
            return x + y;
        };
        Math minus = (x, y) -> {
            return x - y;
        };
        System.out.println("7+5 = " + summa.call(7, 5));
        System.out.println("7-5 = " + minus.call(7, 5));

        int i = mathFunc(summa, 5, 6) - mathFunc(summa, 2, 3);//11-5=6
        System.out.println("11-5 = " + i);
    }
}
