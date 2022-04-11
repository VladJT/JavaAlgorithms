package com.company;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.plaf.TableHeaderUI;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class CodeWars2 {
//Любая живая клетка с менее чем двумя живыми соседями умирает, как если бы это было вызвано недостаточной заселенностью.
//Любая живая клетка с более чем тремя живыми соседями умирает, как бы от перенаселения.
//Любая живая клетка с двумя или тремя живыми соседями живет до следующего поколения.
//Любая мертвая ячейка с ровно тремя живыми соседями становится живой ячейкой.

    public static int[][] getGeneration(int[][] cells, int generations) {
        for (int i = 0; i < cells.length; i++) {
            System.out.println(Arrays.toString(cells[i]));
        }
        System.out.println("-----> " + generations);

        int[][] map = cells.clone();
        for (int i = 0; i < generations; i++) {
            map = getCellMap(map);
        }

        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        return map;
    }

    public static int[][] getCellMap(int[][] cells) {
        int[][] cellMap = new int[cells.length + 2][cells[0].length + 2];
        for (int i = 0; i < cellMap.length; i++)
            for (int j = 0; j < cellMap[0].length; j++) {
                if (i == 0 || j == 0 || i == cellMap.length - 1 || j == cellMap[0].length - 1) {
                    cellMap[i][j] = 0;
                } else
                    cellMap[i][j] = cells[i - 1][j - 1];
            }

        int[][] newCellMap = new int[cellMap.length][cellMap[0].length];
        for (int i = 0; i < cellMap.length; i++)
            for (int j = 0; j < cellMap[0].length; j++) {
                int nearCellsCount = getNearAliveCellsCount(cellMap, i, j);
                //alive cell
                if (cellMap[i][j] == 1) {
                    if (nearCellsCount == 2 || nearCellsCount == 3) newCellMap[i][j] = 1;
                    else newCellMap[i][j] = 0;
                }// dead cell
                else {
                    if (nearCellsCount == 3) newCellMap[i][j] = 1;
                    else newCellMap[i][j] = 0;
                }
            }

        newCellMap = trimMap(newCellMap);

        return newCellMap;
    }

    private static int[][] trimMap(int[][] newCellMap) {
        int startRow = -1, endRow = -1;
        int startCol = -1, endCol = -1;
        for (int i = 0; i < newCellMap.length; i++) {
            int sumRow = Arrays.stream(newCellMap[i]).sum();
            if (sumRow > 0) {
                if (startRow == -1) startRow = i;
                endRow = i;
            }
        }

        for (int j = 0; j < newCellMap[0].length; j++) {
            int sumCol = 0;
            for (int i = 0; i < newCellMap.length; i++) {
                sumCol += newCellMap[i][j];
            }
            if (sumCol > 0) {
                if (startCol == -1) startCol = j;
                endCol = j;
            }
        }

        if (startRow == -1 && endRow == -1 && startCol == -1 && endCol == -1) {
            return new int[0][0];
        }

        int[][] newMap = new int[endRow - startRow + 1][endCol - startCol + 1];
        for (int i = 0; i < newMap.length; i++)
            for (int j = 0; j < newMap[0].length; j++)
                newMap[i][j] = newCellMap[i + startRow][j + startCol];
        return newMap;
    }

    private static int getNearAliveCellsCount(int[][] cellMap, int i, int j) {
        int count = 0;
        for (int x = i - 1; x <= i + 1; x++)
            for (int y = j - 1; y <= j + 1; y++) {
                if (x == i && y == j) continue;//current cell not count

                if (x >= 0 && x < cellMap.length && y >= 0 && y < cellMap[0].length)
                    count += cellMap[x][y];
            }
        return count;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------
        int[][][] gliders = {
                {{1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}},
                {{0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}}
        };

      //  int[][] res = getGeneration(gliders[0], 1);
        int[][] arr = {{1, 1, 1, 0, 0, 0, 1, 0},
                       {1, 0, 0, 0, 0, 0, 0, 1},
                       {0, 1, 0, 0, 0, 1, 1, 1}};
        int[][] rez = getGeneration(arr, 16);

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

}