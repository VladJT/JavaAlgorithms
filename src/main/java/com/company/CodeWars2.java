package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class CodeWars2 {


    enum DIRECTION {
        RIGHT, DOWN, LEFT, UP;

        public DIRECTION getNextDirection() {
            if (this==RIGHT) return DOWN;
            if (this==DOWN) return LEFT;
            if (this==LEFT) return UP;
            if (this==UP) return RIGHT;
            return null;
        }
    }


    public static int[] snail(int[][] array) {
        int n = array.length;
        int[] rez = new int[n * n];

        DIRECTION curDir = DIRECTION.RIGHT;

        int x = 0, y = 0;
        for (int i = 0; i < n * n; i++) {
            System.out.println(i + ", " + curDir + ", ");
            switch (curDir) {
                case RIGHT -> {
                    curDir = curDir.getNextDirection();
                }
                case DOWN -> {
                    curDir = curDir.getNextDirection();
                }
                case LEFT -> {
                    curDir = curDir.getNextDirection();
                }
                case UP -> {
                    curDir = curDir.getNextDirection();
                }
            }

        }

        return rez;
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