package com.company;

import java.util.*;


public class CodeWars {


    public static int countPassengers(ArrayList<int[]> stops) {
        //Code here!
        int count = 0;
        for (int[] a : stops) {
            count = count + a[0] - a[1];
        }
        return count;
    }


    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{10, 0});
        list.add(new int[]{3, 5});
        list.add(new int[]{2, 5});
        System.out.println(countPassengers(list));


    }
}
