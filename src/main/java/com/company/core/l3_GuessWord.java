package com.company.core;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом
 * и сообщает, правильно ли ответил пользователь.
 * Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
 * apple – загаданное
 * apricot - ответ игрока
 * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
 * Для сравнения двух слов посимвольно можно пользоваться:
 * String str = "apple";
 * char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
 * Играем до тех пор, пока игрок не отгадает слово.
 * Используем только маленькие буквы.
 */
class l3_GuessWord {

    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        System.out.println("Задан список слов, угадайте случайно загаданное слово 🎭:");
        System.out.println(Arrays.toString(words));

        int searchIndex = new Random().nextInt(words.length);
        String userAnswer = "";
        boolean isCorrectAnswer = false;

        //TODO delete
        System.out.println("Компьютер загадал " + words[searchIndex]);

        while (!isCorrectAnswer) {
            System.out.print("Введите ваш вариант: ");
            userAnswer = new Scanner(System.in).next();

            isCorrectAnswer = userAnswer.equals(words[searchIndex]);
            if (!isCorrectAnswer) {
                showHint(userAnswer, words[searchIndex]);
            }
        }

        System.out.println("Вы угадали! Компьютер загадал " + words[searchIndex]);
    }

    // показ посказки
    public static void showHint(String userAnswer, String correctAnswer) {
        String result = "";
        for (int i = 0; i < 15; i++) {
            if (userAnswer.length() > i && correctAnswer.length() > i) {
                if (userAnswer.charAt(i) == correctAnswer.charAt(i))
                    result += userAnswer.charAt(i);
                else result += "#";
            } else result += "#";
        }
        System.out.println(result);
    }

}
