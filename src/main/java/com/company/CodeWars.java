package com.company;


import java.math.BigInteger;
import java.util.*;

class CodeWars extends Object {

    static int[] arr = {0, 0, 0, 0, 0};//2, 3, 5 ,7,11
    static int[] arrValue = {2, 3, 5, 7, 11};//2, 3, 5 ,7,11

    static String decomp4(int n) {

        String rezString = "";
        for (int i = 0; i < arr.length; i++) arr[i]=0;

        BigInteger fact = BigInteger.ONE;
        int newI = 1;

        for (int i = 2; i <= n; i++) {
            newI = i;
            newI = optimize(newI, 0, 2);
            newI = optimize(newI, 1, 3);
            newI = optimize(newI, 2, 5);
            newI = optimize(newI, 3, 7);
            newI = optimize(newI, 4, 11);

            fact = fact.multiply(BigInteger.valueOf(newI));
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) rezString += arrValue[i]+((arr[i] == 1) ? "" : "^" + arr[i]) + " * ";
        }

        BigInteger divPrimeNum = new BigInteger("13");

        int count = 0;
        while (!fact.equals(BigInteger.ONE)) {
            if (fact.remainder(divPrimeNum).equals(BigInteger.ZERO)) {
                fact = fact.divide(divPrimeNum);
                count++;
                if (count == 1) rezString += divPrimeNum.intValue();
                continue;
            }
            rezString += ((count == 1) ? "" : "^" + count) + " * ";
            count = 0;
            divPrimeNum = divPrimeNum.nextProbablePrime();
        }//while

        if (rezString.substring(rezString.length()-3).equals(" * ")) {
            rezString = rezString.substring(0,rezString.length()-3);
        }
        return rezString;
    }

    static int optimize(int newI, int index, int divNum) {
        while (newI % divNum == 0) {
            arr[index]++;
            newI /= divNum;
        }
        return newI;
    }

    public static String decomp3(int n) {

        String rezString = "";

        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            fact = fact.multiply(BigInteger.valueOf(i));

        BigInteger divPrimeNum = new BigInteger("2");

        int count = 0;
        while (!fact.equals(BigInteger.ONE)) {
            if (fact.remainder(divPrimeNum).equals(BigInteger.ZERO)) {
                fact = fact.divide(divPrimeNum);
                count++;
                if (count == 1) rezString += divPrimeNum.intValue();
                continue;
            }
            //rezString += divPrimeNum.intValue() + ((count == 1) ? "" : "^" + count)+" * ";
            rezString += ((count == 1) ? "" : "^" + count) + " * ";
            count = 0;
            divPrimeNum = divPrimeNum.nextProbablePrime();
        }//while

        return rezString;
    }

    public static String decomp2(int n) {

        ArrayList<Integer> s = new ArrayList<>();

        BigInteger divPrimeNum = new BigInteger("2");
        int divN = 2;
        int fact = 0;

        for (int i = 2; i <= n; i++) {
            fact = i;
            divPrimeNum = BigInteger.TWO;
            divN = 2;

            while (fact > 1) {
                if (fact % divN == 0) {
                    fact = fact / divN;
                    s.add(divN);
                } else {
                    divPrimeNum = divPrimeNum.nextProbablePrime();
                    divN = divPrimeNum.intValue();
                }
            }//while
        }

        Collections.sort(s);

        String rezString = "";
        int count = 0;
        int nextNum;
        int curNum;
        int size = s.size();

        for (int i = 0; i < size; i++) {
            count++;
            curNum = s.get(i);

            if (i == size - 1)
                nextNum = -1;
            else
                nextNum = s.get(i + 1);

            if (curNum != nextNum) {
                rezString += s.get(i) + ((count == 1) ? "" : "^" + count);
                count = 0;
                if (i != size - 1) rezString += " * ";
            }

        }
        return rezString;
    }

    public static String decomp(int n) {

        HashMap<Integer, Integer> s = new HashMap<>();

        BigInteger divPrimeNum = new BigInteger("2");
        int divN = 2;
        int fact = 0;
        int value = 0;
        for (int i = 2; i <= n; i++) {
            fact = i;
            divPrimeNum = BigInteger.TWO;
            divN = 2;

            while (fact > 1) {
                if (fact % divN == 0) {
                    fact = fact / divN;
                    value = s.containsKey(divN) ? s.get(divN) : 0;
                    s.put(divN, ++value);
                } else {
                    divPrimeNum = divPrimeNum.nextProbablePrime();
                    divN = divPrimeNum.intValue();
                }
            }//while
        }


        String rezString = "";

        divPrimeNum = BigInteger.TWO;

        for (int i = 0; i < s.size(); i++) {
            value = s.get(divPrimeNum.intValue());
            rezString += divPrimeNum + ((value == 1) ? "" : "^" + value);
            if (i != s.size() - 1) {
                rezString += " * ";
                divPrimeNum = divPrimeNum.nextProbablePrime();
            } else break;
            while (!s.containsKey(divPrimeNum.intValue())) {
                divPrimeNum = divPrimeNum.nextProbablePrime();
            }
        }
        return rezString;
    }


    static boolean isPrime(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(number));
        return probablePrime;
    }


    public static void main(String[] args) {
        System.out.println(decomp4(5));//2^22 * 3^10 * 5^6 * 7^3 * 11^2 * 13 * 17 * 19 * 23
        // 5 = 2^3 * 3 * 5


    }


}


