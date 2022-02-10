package com.company;

import java.util.*;
import java.util.function.BiFunction;

// функциональный интерфейс
interface Expression{
    long call(long a, long b);
}

public class CodeWars {

    class Operarray {

        public static long gcdi(long x, long y) {
            long min = Math.min(Math.abs(x), Math.abs(y));
            long max = Math.max(Math.abs(x), Math.abs(y));
            long gcdi = min;
            while (gcdi > 1) {
                if (max % gcdi == 0 && min % gcdi == 0) break;
                gcdi--;
            }
            return gcdi;
        }

        public static long lcmu(long a, long b) {
            // your code
            long min = Math.min(Math.abs(a), Math.abs(b));
            long max = Math.max(Math.abs(a), Math.abs(b));
            long sum = min;
            while (sum % max != 0) {
                sum += min;
            }
            return sum;
        }

        public static long som(long a, long b) {
            return a + b;
        }

        public static long maxi(long a, long b) {
            return a > b ? a : b;
        }

        public static long mini(long a, long b) {
            return a > b ? b : a;
        }

        public static long[] operArray(Expression operator, long[] arr, long init) {
            long[] newArr = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = operator.call(arr[i], init);
                init = newArr[i];
            }

            return newArr;
        }

    }

    public static void main(String[] args) {

        long[] a = new long[]{18, 69, -90, -78, 65, 40};


        System.out.println(Arrays.toString(Operarray.operArray(Operarray::gcdi, a, 18)));
        
        BiFunction<Integer, Integer, Integer> mul = (x, y) -> x * y;

        System.out.println(mul.apply(5,2));

    }


}


