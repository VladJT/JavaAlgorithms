package com.company;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.plaf.TableHeaderUI;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class CodeWars2 {

    static Map<String, Integer> dictionary = new HashMap<>() {{
        put("zero", 0);
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
        put("ten", 10);
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
    }};

    public static int parseInt(String numStr) {
        int n = 1;
        int rez = 0;

        String[] st = numStr.split("[\\s-]");
        for (int i = st.length - 1; i >= 0; i--) {
            if (dictionary.containsKey(st[i])) {
                rez += dictionary.get(st[i]) * n;
            } else {
                if (st[i].equals("hundred")) n *= 100;
                if (st[i].equals("thousand")) n = 1000;
                if (st[i].equals("million")) n = 1000000;
            }
        }
        return rez;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------

        System.out.println(parseInt("five hundred twenty-one"));// 541
        System.out.println(parseInt("one million"));//
        System.out.println(parseInt("two hundred six"));//206
        System.out.println(parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));//783919


        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

}