package com.company;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class CodeWars extends Object {

    private static class MyTime {
        private long sTime = 0;

        public void start() {
            sTime = System.currentTimeMillis();
        }

        public void end() {
            sTime = System.currentTimeMillis() - sTime;
        }

        public void printTime() {
            System.out.println("Операция заняла: " + sTime + " милисек.");
        }
    }

    private static MyTime timer = new MyTime();

    static Set<String> bananas(final String s) {
        timer.start();
        Set<String> stSet = new HashSet<>();
        if (s.equals("banana")) {
            stSet.add(s);
            return stSet;
        }

        String tempS = s.substring(0, s.length() - 5);

        int bIndex = tempS.indexOf('b');
        while (true) {

            // todo


            tempS = s.substring(bIndex + 1, s.length() - 5);
            if (!tempS.contains("b")) break;
            bIndex = (bIndex + 1) + tempS.indexOf('b');
        }


        int[] arr = new int[s.length() - 6];
        int[] maxArr = new int[s.length() - 6];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = bIndex + i;
            maxArr[i] = i + 6;

        }

        Thread[] t = new Thread[1000000];
        int counter = 0;

        while (!Arrays.equals(arr, maxArr)) {
            t[counter] = new Thread(new Runnable() {
                @Override
                public void run() {
                    checkBanana(s, stSet, arr);

                    boolean exit = false;
                    do {
                        for (int i = 0; i < arr.length; i++) {
                            if (arr[i] < maxArr[i]) {
                                arr[i]++;
                                break;
                            } else {
                                arr[i] = i;
                            }
                        }
                        if (Arrays.equals(arr, maxArr)) exit = true;


                        int firstN = 0;

                        while (true) {
                            int finalFirstN = firstN;
                            if (Arrays.stream(arr).filter(n -> n == finalFirstN).count() == 0) break;
                            firstN++;
                        }
                        if (s.charAt(firstN) == 'b') exit = true;

                    } while (exit == false);

                    // последняя итерация
                    if (Arrays.equals(arr, maxArr)) {
                        checkBanana(s, stSet, maxArr);
                    }
                }
            });

            t[counter].start();
            try {
                t[counter].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }


        System.out.println(counter + " !!");

        timer.end();
        timer.printTime();
        return stSet.isEmpty() ? Collections.EMPTY_SET : stSet;
    }


    static Set<String> bananas_old(final String s) {
        timer.start();
        Set<String> stSet = new HashSet<>();
        if (s.equals("banana")) {
            stSet.add(s);
            return stSet;
        }

        String tempS = s.substring(0, s.length() - 5);

        int bIndex = tempS.indexOf('b');


        int[] arr = new int[s.length() - 6];
        int[] maxArr = new int[s.length() - 6];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = bIndex + i;
            maxArr[i] = i + 6;

        }

        Thread[] t = new Thread[1000000];
        int counter = 0;

        while (!Arrays.equals(arr, maxArr)) {
            t[counter] = new Thread(new Runnable() {
                @Override
                public void run() {
                    checkBanana(s, stSet, arr);

                    boolean exit = false;
                    do {
                        for (int i = 0; i < arr.length; i++) {
                            if (arr[i] < maxArr[i]) {
                                arr[i]++;
                                break;
                            } else {
                                arr[i] = i;
                            }
                        }
                        if (Arrays.equals(arr, maxArr)) exit = true;


                        int firstN = 0;

                        while (true) {
                            int finalFirstN = firstN;
                            if (Arrays.stream(arr).filter(n -> n == finalFirstN).count() == 0) break;
                            firstN++;
                        }
                        if (s.charAt(firstN) == 'b') exit = true;

                    } while (exit == false);

                    // последняя итерация
                    if (Arrays.equals(arr, maxArr)) {
                        checkBanana(s, stSet, maxArr);
                    }
                }
            });

            t[counter].start();
            try {
                t[counter].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }


        System.out.println(counter + " !!");

        timer.end();
        timer.printTime();
        return stSet.isEmpty() ? Collections.EMPTY_SET : stSet;
    }

    private static void checkBanana(String s, Set<String> stSet, int[] arr) {
        String rez = getBananaString(s, arr);
        if (rez.replaceAll("-", "").equals("banana")) {
            stSet.add(rez);
        }
    }

    private static String getBananaString(String s, int[] arr) {
        char[] c = s.toCharArray();
        for (int j : arr) c[j] = '-';
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String input = "baaanannanbn";//[b--ana-na---, ba--na-na---, b-a-na-na---, b--anan-a---, b-a-nan-a---, ba--nan-a---]
        Set<String> expected = bananas(input);
        for (String s : expected) {
            System.out.println(s);
        }
    }


}


