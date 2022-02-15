package com.company.core;

import java.util.Random;
import java.util.Scanner;

/**
 * Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
 * При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
 * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
 */
class l3_GuessNumber {

    public static void main(String[] args) {
        int playGame = 1;
        do {
            int tryCount = 3;
            int xNumber = new Random().nextInt(10);
            boolean isWin = false;

            System.out.printf(xNumber + " Компьютер загадал число от 0 до 9. Попробуйте его угадать %d за попытки%n", tryCount);
            do {
                isWin = checkAnswer(xNumber);
                tryCount--;
            } while (tryCount > 0 && isWin == false);

            if (isWin)
                System.out.println("Поздравляем🎈🎈🎈  Вы победили!Оставалось попыток: " + tryCount);
            else System.out.println("Вы проиграли! Компьютер загадал число " + xNumber);

            System.out.println("\nПовторить игру еще раз? 1 – да / 0 – нет");
            playGame = new Scanner(System.in).nextInt();
        } while (playGame == 1);
    }

    public static boolean checkAnswer(int xNumber) {
        System.out.print("Введите число: ");
        int answer = new Scanner(System.in).nextInt();
        if (answer > xNumber) System.out.println("Загаданное число меньше");
        if (answer < xNumber) System.out.println("Загаданное число больше");
        return answer == xNumber;
    }

}
