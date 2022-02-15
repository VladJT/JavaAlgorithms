package com.company.recursion;

class Main {

    public static void main(String[] args) {
        System.out.println("fibonacci= " + fibonacci(6));
        System.out.println("5! = " + factorial(5));
    }


    public static int fibonacci(int n) {
        if (n <= 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        else return n * factorial(n - 1);
    }
}
