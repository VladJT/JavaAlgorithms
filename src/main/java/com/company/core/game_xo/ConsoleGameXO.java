package com.company.core.game_xo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// игра Крестики-Нолики
class ConsoleGameXO {

    private static int SIZE = 3;// размер игрового поля
    private static int WIN_SIZE = 3;// длина линии до победы в партии

    private static char[][] map;// матрица данных

    private static final char DOT_EMPTY = '_';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final String DELIMITER = "  ";

    public static int currentX = 0;// координаты последнего хода человека или AI
    public static int currentY = 0;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();


    public static void main(String[] args) throws InterruptedException {
        do {
            do {
                System.out.print("Введите размер поля (3-12): ");
                SIZE = scanner.nextInt();
            } while (SIZE < 3 || SIZE > 12);


            if (SIZE >= 5 && SIZE <= 10) WIN_SIZE = 4;
            else if (SIZE > 10) WIN_SIZE = 5;
            else WIN_SIZE = 3;//default

            System.out.println(COLORS.YELLOW + "ИГРА \"Крестики-нолики\" 👑. Для победы в игре требуется линия длиной " + COLORS.BLUE + WIN_SIZE);
            COLORS.resetColors();

            initMap();
            printMap();

            while (true) {
                humanTurn();
                if (isEndGame(DOT_X)) {
                    break;
                }

                TimeUnit.MILLISECONDS.sleep(800);// пауза перед ходом AI
                aiTurn();
                if (isEndGame(DOT_O)) {
                    break;
                }

            }
        } while (isContinueGame());
        System.out.println("Игра окончена");
    }


    private static boolean isContinueGame() {
        System.out.println("Сыграем еще? 1(y) - да, 0(n) -нет");
        scanner.nextLine();
        String s = scanner.nextLine().toLowerCase();
        return s.equals("1") || s.equals("да") || s.equals("y");
    }


    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }


    private static void printMap() {
        printMap(-1, -1, COLORS.SYS);
    }

    private static void printMap(int x, int y, String color) {
        printTopNumLine();// линии координат
        // выводим map
        for (int i = 0; i < SIZE; i++) {
            printLeftNum(i + 1);
            for (int j = 0; j < SIZE; j++) {
                if (i == x && y == j) System.out.print(color + map[i][j] + COLORS.SYS + DELIMITER);
                else System.out.print(map[i][j] + DELIMITER);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printTopNumLine() {
        COLORS.setGreyBackgroundColor();
        for (int i = 0; i <= SIZE; i++)
            //System.out.print(i + DELIMITER);
            System.out.printf("%-" + (DELIMITER.length() + 1) + "d", i);
        COLORS.resetColors();
        System.out.println();
    }

    private static void printLeftNum(int i) {
        COLORS.setGreyBackgroundColor();
        //System.out.print(i + DELIMITER);
        System.out.printf("%-" + (DELIMITER.length() + 1) + "d", i);
        COLORS.resetColors();
    }

    private static boolean isEndGame(char playerSymbol) {
        if (checkWin(playerSymbol)) {
            System.out.println(COLORS.YELLOW + "Победили " + playerSymbol);
            COLORS.resetColors();
            return true;
        }
        if (isMapFull()) {
            System.out.println(COLORS.YELLOW + "Ничья!");
            COLORS.resetColors();
            return true;
        }
        return false;
    }

    private static boolean checkWin(char playerSymbol) {
//        if (checkWinAllDiagonals(playerSymbol) || checkWinAllLines(playerSymbol)) {
//            return true;
//        }
        if (checkWinCurrentLine(playerSymbol) || checkWinCurrentDiagonals(playerSymbol)) {
            return true;
        }
        return false;
    }


    private static boolean checkWinCurrentLine(char playerSymbol) {
        int countH = 0;
        int countV = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[currentX][i] == playerSymbol) countH++;
            else countH = 0;
            if (map[i][currentY] == playerSymbol) countV++;
            else countV = 0;

            if (countH == WIN_SIZE || countV == WIN_SIZE) return true;
        }
        return false;
    }


    private static boolean checkWinCurrentDiagonals(char playerSymbol) {
        int count1 = 0;
        int count2 = 0;
        // ищем стартовую точку для диагонали /
        int x1 = currentX;
        int y1 = currentY;
        while (x1 < SIZE && y1 > 0) {
            x1++;
            y1--;
        }
        // ищем стартовую точку для диагонали \
        int x2 = currentX;
        int y2 = currentY;
        while (x2 > 0 && y2 > 0) {
            x2--;
            y2--;
        }

        for (int i = 0; i < SIZE; i++) {
            // диагональ /
            if (isCellValid(x1, y1) && map[x1][y1] == playerSymbol)
                count1++;
            else count1 = 0;
            x1--;
            y1++;

            // диагональ \
            if (isCellValid(x2, y2) && map[x2][y2] == playerSymbol)
                count2++;
            else count2 = 0;
            x2++;
            y2++;

            if (count1 == WIN_SIZE || count2 == WIN_SIZE) return true;
        }
        return false;
    }


    private static boolean checkWinAllLines(char playerSymbol) {
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

    private static boolean checkWinAllDiagonals(char playerSymbol) {
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

    private static void setSymbolToMap(int x, int y, char playerSymbol) {
        map[x][y] = playerSymbol;
        currentX = x;
        currentY = y;
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


//    private static void humanTurn() {
//        int x, y;
//        do {
//            System.out.print("Введите координаты свободной ячейки: ");
//            x = scanner.nextInt() - 1;
//            y = scanner.nextInt() - 1;
//        } while (!isCellValidForMove(x, y));
//        setSymbolToMap(x, y, DOT_X);
//        printMap(x, y, COLORS.GREEN);
//    }


    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты свободной ячейки (через пробел): ");
            x = getValidNumberFromUser() - 1;
            y = getValidNumberFromUser() - 1;
        } while (!isCellValidForMove(x, y));
        setSymbolToMap(x, y, DOT_X);
        printMap(x, y, COLORS.GREEN);
    }

    private static int getValidNumberFromUser() {
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (isNumberValid(n)) return n;
                else {
                    System.out.println("Проверьте значение. Допустимый диапазон: 1.." + SIZE);
                    scanner.next();
                }
            } else {
                scanner.next();
                System.out.println("Ввод допускает лишь целые числа");
            }
        }
    }

    private static boolean isNumberValid(int n) {
        return n >= 1 && n <= SIZE;
    }


    /**
     * ЛОГИКА КОМПЬЮТЕРА
     */
    private static void aiTurn() {
        // рейтинг ходов
        int[][] moveRating = initMoveRating();
        int maxRating = 0;
        int x = -1;
        int y = -1;

        // ИЩЕМ ОПТИМАЛЬНЫЙ ХОД (проставляя рейтинги возможным ходам)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // 1. проверяем , что есть возможность хода
                if (!isCellValidForMove(i, j)) continue;

                // 2. проверяем может ли этим ходом Ai победить сразу (+20 к рейтингу)
                setSymbolToMap(i, j, DOT_O);
                if (checkWin(DOT_O)) {
                    moveRating[i][j] += 20;
                }
                map[i][j] = DOT_EMPTY;

                // 3. проверяем может ли ход помешать победить Игроку (+10 к рейтингу)
                //  if (checkCanWin(i, j, DOT_X)) moveRating[i][j] += 10;
                setSymbolToMap(i, j, DOT_X);
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

        showAiMoveRating(moveRating);// вывести матрицу приоритета ходов AI

        // если приоритета нет - ходим случайно
        if (maxRating < 1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValidForMove(x, y));
        }
        printAiTurn(x, y);
    }

    private static int[][] initMoveRating() {
        int[][] moveRating = new int[SIZE][SIZE];
        for (int i = 0; i < moveRating.length; i++)
            Arrays.fill(moveRating[i], 0);
        return moveRating;
    }


    private static void printAiTurn(int x, int y) {
        System.out.println("Компьютер выбрал ячейку: " + (x + 1) + ", " + (y + 1));
        setSymbolToMap(x, y, DOT_O);
        printMap(x, y, COLORS.RED);
    }


    public static void showAiMoveRating(int[][] moveRating) {
        System.out.println(COLORS.GREY + "Матрица приоритета ходов AI:");
        printTopNumLine();

        for (int i = 0; i < SIZE; i++) {
            printLeftNum(i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf(COLORS.GREY + "%-3d", moveRating[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------" + COLORS.SYS);
    }

}
