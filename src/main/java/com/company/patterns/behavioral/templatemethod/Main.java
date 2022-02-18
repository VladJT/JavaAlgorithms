package com.company.patterns.behavioral.templatemethod;


/**
 * <h1>Шаблонный метод (Template method)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * поведенческий паттерн, задающий скелет алгоритма в суперклассе и заставляющий подклассы реализовать конкретные шаги этого алгоритма<br>
 * шаблонный метод определяет основу алгоритма и позволяет подклассам переопределить некоторые его шаги, не изменяя структуру в целом *
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Шаблонные методы можно встретить во многих библиотечных классах Java. Разработчики создают их, чтобы позволить клиентам легко и быстро расширять стандартный код при помощи наследования.
 * <p>
 * <font color="#fa8e47">Примеры Шаблонных методов в стандартных библиотеках Java:<br></font>
 * Все не-абстрактные методы классов java.io.InputStream, java.io.OutputStream, java.io.Reader и java.io.Writer.<br>
 * Все не-абстрактные методы классов java.util.AbstractList, java.util.AbstractSet и java.util.AbstractMap.
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Класс заставляет своих потомков реализовать методы-шаги, но самостоятельно реализует структуру алгоритма.
 * <p>
 * <font color="#fa8e47">Использование паттерна Шаблонный метод позволяет:<br></font>
 * <li>устранить дублирование кода</li>
 * <li>повысить переиспользуемость кода за счет наследования</li>
 * <li>сделать код более универсальным и легко изменяемым под новые условия</li>
 *
 * <font color="fa8eff">ВАЖНО!!! Таким образом, подклассы абстрактного класса не должны иметь возможность переопределить шаблонный метод с целью измения логики его работы.
 * По этой причине шаблонные методы объявляются как final и не могут использовать интерфейсы с их методами по умолчанию вместо абстрактных классов.
 * Вся идея паттерна в этом случае будет нарушена.</font>
 */

abstract class Game {
    private int playersAmount;

    protected abstract void initializeGame();

    protected abstract void playGame();

    private void endGame() {
        System.out.println("Игра окончена");
    }

    protected abstract void printWinner();

    // шаблонные методы обязательно должны быть final!
    public final void playOneGame(int playersAmount) {
        this.playersAmount = playersAmount;
        initializeGame();
        playGame();
        endGame();
        printWinner();
    }
}


class Chess extends Game {
    @Override
    protected void initializeGame() {
        System.out.println("Начинаем игру шахматы");
    }

    @Override
    protected void playGame() {
        System.out.println("ИГРАЕМ в шахматы");
    }

    @Override
    protected void printWinner() {
        System.out.println("ПОБЕДА ♟️");
    }
}

class Monopoly extends Game {
    @Override
    protected void initializeGame() {
        System.out.println("Начинаем игру Монополия");
    }

    @Override
    protected void playGame() {
        System.out.println("ИГРАЕМ в Монополию 🪙");
    }

    @Override
    protected void printWinner() {
        System.out.println("Победил 🤷️");
    }
}


class Main {
    public static void main(String[] args) {
        Game game1 = new Chess();
        game1.playOneGame(2);
        System.out.println("....");
        Game game2 = new Monopoly();
        game2.playOneGame(4);
    }
}
