package com.company;

import javax.swing.plaf.TableHeaderUI;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class CodeWars2 {
    /*
                ┌───┬───┬───┐
                │ 1 │ 2 │ 3 │
                ├───┼───┼───┤
                │ 4 │ 5 │ 6 │
                ├───┼───┼───┤
                │ 7 │ 8 │ 9 │
                └───┼───┼───┘
                    │ 0 │
                    └───┘
    */

    public static List<String> getPINs(String observed) {
        System.out.println(observed);
        int len = observed.length();
        ArrayList<ArrayList<Integer>> variants = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            variants.add(new ArrayList());
            switch (observed.charAt(i)) {
                case '1':
                    variants.get(i).addAll(Arrays.asList(1, 2, 4));
                    break;
                case '2':
                    variants.get(i).addAll(Arrays.asList(1, 2, 3, 5));
                    break;
                case '3':
                    variants.get(i).addAll(Arrays.asList(2, 3, 6));
                    break;
                case '4':
                    variants.get(i).addAll(Arrays.asList(1, 4, 5, 7));
                    break;
                case '5':
                    variants.get(i).addAll(Arrays.asList(2, 4, 5, 6, 8));
                    break;
                case '6':
                    variants.get(i).addAll(Arrays.asList(3, 5, 6, 9));
                    break;
                case '7':
                    variants.get(i).addAll(Arrays.asList(4, 7, 8));
                    break;
                case '8':
                    variants.get(i).addAll(Arrays.asList(5, 7, 8, 9, 0));
                    break;
                case '9':
                    variants.get(i).addAll(Arrays.asList(6, 8, 9));
                    break;
                case '0':
                    variants.get(i).addAll(Arrays.asList(8, 0));
                    break;
            }
        }//for


        List<String> result = Arrays.asList("");
        for (int i = 0; i < len; i++) {
            List<String> tmp = new ArrayList<>();
            for (Object cc : variants.get(i).toArray()) {
                for (String s : result) tmp.add(s + cc);
            }
            result = tmp;
        }

        return result;
    } // getPINs


    private static void addCombinations(ArrayList<ArrayList<Integer>> variants, Set<String> result, int[] arr) {
        String comb = "";
        for (int i = 0; i < arr.length; i++) {
            comb += variants.get(i).get(arr[i]);
        }
        result.add(comb);

        for (int i = 0; i < arr.length; i++) {
            int x = arr[i] + 1;
            if (x < variants.get(i).size()) {
                StringBuilder sb = new StringBuilder(comb);
                sb.setCharAt(i, variants.get(i).get(x).toString().charAt(0));

                if (!result.contains(sb.toString())) {
                    int[] t = arr.clone();
                    t[i] = x;
                    addCombinations(variants, result, t);
                }

            }
        }
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        System.out.println(getPINs("8"));// new String[]{"5", "7", "8", "9", "0"});
        System.out.println(getPINs("11"));//{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
        System.out.println(getPINs("36935"));//{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
        System.out.println(getPINs("945158"));//{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
        System.out.println("Время выполнения (сек.): " + (System.currentTimeMillis() - startTime));

    }

}