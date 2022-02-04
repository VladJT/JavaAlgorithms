package com.company;

import java.util.*;


public class CodeWars {

    /**
     * digPow(89, 1) should return 1 since 8¹ + 9² = 89 = 89 * 1
     * digPow(92, 1) should return -1 since there is no k such as 9¹ + 2² equals 92 * k
     * digPow(695, 2) should return 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2
     * digPow(46288, 3) should return 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
     */
    public static long digPow(int n, int p) {
        Stack s = new Stack<Integer>();
        int x = n;
        while (x >= 1) {
            int digit = x % 10;
            x = x / 10;
            s.push(digit);
            System.out.println(digit);
        }

        int sum = 0;
        while (!s.isEmpty()) {
            sum += mult((int) s.pop(), p);
            p++;
        }

        System.out.println(sum);
        if (sum % n == 0) return sum / n;
        else return -1;
    }

    public static int mult(int x, int y) {

        if (y == 1) return x;
        else return x * mult(x, y - 1);
    }


    public static void main(String[] args) {
        System.out.println(digPow(1000, 2));
     //   System.out.println(digPow(46288, 3));

    }
}
