package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class CodeWars2 {

    public static int[] snail(int[][] array) {

        return null;
    }


    public static void main(String[] args) {
        int[][] array
                = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[] r = {1, 2, 3, 6, 9, 8, 7, 4, 5};
        System.out.println(Arrays.asList(snail(array)));//{1, 2, 3, 6, 9, 8, 7, 4, 5};

    }


}