package com.company.game_xo;

import java.util.Random;
import java.util.Scanner;

public class ConsoleGameXO {

    private static int SIZE = 3;
    private static char[][] map;


    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            if (isEndGame(DOT_X)) {
                break;
            }

            aiTurn();
            if (isEndGame(DOT_O)) {
                break;
            }

        }
        System.out.println("Игра окончена");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    private static void printMap() {
        // линии координат
        for (int i = 0; i <= SIZE; i++)
            System.out.print(i + " ");
        System.out.println();

        // выводим map
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isEndGame(char playerSymbol) {
        printMap();
        if (checkWin(playerSymbol)) {
            System.out.println("Победили " + playerSymbol);
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean checkWin(char playerSymbol) {
        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) {
            return true;
        }
        return false;
    }

    private static boolean checkWinLines(char playerSymbol) {
        boolean cols, rows;

        for (int col = 0; col < SIZE; col++) {
            cols = true;
            rows = true;
            for (int row = 0; row < SIZE; row++) {
                cols &= (map[col][row] == playerSymbol);
                rows &= (map[row][col] == playerSymbol);
            }
            if (cols || rows) return true;
        }
        return false;
    }

    private static boolean checkWinDiagonals(char playerSymbol) {
        boolean leftRight, rightLeft, result;
        leftRight = true;
        rightLeft = true;

        for (int i = 0; i < SIZE; i++) {
            leftRight &= (map[i][i] == playerSymbol);
            rightLeft &= (map[SIZE - i - 1][i] == playerSymbol);
        }

        if (leftRight || rightLeft) return true;
        else return false;
    }


    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY) return false;
        return true;
    }


    private static boolean isCellValid(int x, int y) {
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
    }

    private static boolean isCellValidForMove(int x, int y) {
        if (!isCellValid(x, y) || map[x][y] != DOT_EMPTY) {
            return false;
        }
        return true;
    }


    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты ячейки через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValidForMove(x, y));
        map[x][y] = DOT_X;
    }


    /**
     * ЛОГИКА КОМПЬЮТЕРА
     */
    private static void aiTurn() {
        // рейтинг ходов
        int[][] moveRating = new int[SIZE][SIZE];
        for (int i = 0; i < moveRating.length; i++)
            for (int j = 0; j < moveRating.length; j++)
                moveRating[i][j] = 0;
        int maxRating = 0;
        int x = -1;
        int y = -1;

        // ИЩЕМ ОПТИМАЛЬНЫЙ ХОД (проставляя рейтинги возможным ходам)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // 1. проверяем , что есть возможность хода
                if (!isCellValidForMove(i, j)) continue;

                // 2. проверяем может ли этим ходом Ai победить сразу (+20 к рейтингу)
                map[i][j] = DOT_O;
                if (checkWin(DOT_O)) {
                    moveRating[i][j] += 10;
                }
                map[i][j] = DOT_EMPTY;


                // 3. проверяем может ли ход помешать победить Игроку (+10 к рейтингу)
                //  if (checkCanWin(i, j, DOT_X)) moveRating[i][j] += 10;
                map[i][j] = DOT_X;
                if (checkWin(DOT_X)) {
                    moveRating[i][j] += 10;
                }
                map[i][j] = DOT_EMPTY;

                // проверяем, есть ли соседние ячейки с DOT_O (+1 к рейтингу)
                for (int ii = i - 1; ii <= (i + 1); ii++)
                    for (int jj = j - 1; jj <= (j + 1); jj++)
                        if (isCellValid(ii, jj)) {
                            if (map[ii][jj] == DOT_O)
                                moveRating[i][j] += 1;
                        }

                // планируем ход в клетку с максимальным  рейтингом
                if (maxRating < moveRating[i][j]) {
                    maxRating = moveRating[i][j];
                    x = i;
                    y = j;
                }
            }// for j
        }// for i

        showAiMoveRating(moveRating);

        // если приоритета нет - ходим случайно
        if (maxRating < 1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValidForMove(x, y));
        }
        printAiTurn(x, y);
    }


    private static void printAiTurn(int x, int y) {
        System.out.println("Компьютер выбрал ячейку: " + (x + 1) + ", " + (y + 1));
        map[x][y] = DOT_O;
    }


    public static void showAiMoveRating(int[][] moveRating) {
        System.out.println("Матрица приоритета ходов");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(moveRating[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

}
