package com.company.core.lambda;


class Main {

    // функциональный интерфейс
    @FunctionalInterface
    interface Math{
        abstract int call(int x, int y);
    }


    // функция высшего порядка
    public static int mathFunc(Math operator, int a, int b) {
        return operator.call(a, b);
    }

    public static void main(String[] args) {
        // functional interface
        Runnable obj = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                System.out.println(this.getClass().getName());
            }
        };
        obj.run();


        //Thread
        new Thread(() -> {
            System.out.println("2");
        }).start();


        // lambda
        Runnable r1 = () -> {
            System.out.println("3");
        };
        new Thread(r1).start();

        Math summa = (x, y) -> {return x+y;};
        Math minus = (x, y) -> {return x-y;};
        System.out.println(summa.call(7,5));
        System.out.println(minus.call(7,5));

        int i = mathFunc(summa, 5, 6) -  mathFunc(summa, 2, 3);//11-5=6
        System.out.println(i);
    }
}
