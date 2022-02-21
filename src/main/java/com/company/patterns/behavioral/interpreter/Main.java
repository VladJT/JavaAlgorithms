package com.company.patterns.behavioral.interpreter;


/**
 * <h1>Интерпретатор (Interpreter) </h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐</font>⭐⭐<p></h2>
 * поведенческий шаблон проектирования, решающий часто встречающуюся, но подверженную изменениям, задачу.<br>
 * Шаблон интерпретатора используется для определения грамматического представления языка и предоставляет интерпретатор для работы с этой грамматикой.
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Шаблон интерпретатора используется в любое время, когда нам нужно оценить грамматику или выражения любого языка.
 * Хорошим примером этого шаблона может быть Google Translate , который интерпретирует ввод и показывает нам вывод на другом языке.<br>
 * Другим примером может быть компилятор Java. Компилятор интерпретирует код Java и переводит его в байт-код, который JVM использует для выполнения операций на устройстве, на котором он работает.<br>
 * Этот шаблон также представляет собой отличный способ написания простых программ, которые понимают синтаксис, подобный человеческому.
 * <p>
 * <font color="#fa8e47">Примеры в стандартных библиотеках Java:<br></font>
 * java.util.Pattern<br>
 * java.text.Normalizer<br>
 * java.text.Format
 * <p>
 * <font color="ffcc00">Важно! Также известен как Little (Small) Language</font>
 */


interface Expression {
    public int interpret(InterpreterEngine engine);
}


// двиижок интерпретатора
class InterpreterEngine {
    private int num1 = 0;
    private int num2 = 0;

    public int add(String input) {
        initNumbers(input);
        return (num1 + num2);
    }

    public int minus(String input) {
        initNumbers(input);
        return (num1 - num2);
    }


    public int multiply(String input) {
        initNumbers(input);
        return (num1 * num2);
    }


    private void initNumbers(String input) {
        String string = input.replaceAll("[^0-9]", " ");
        string = string.replaceAll("( )+", " ").trim();
        String[] tokens = string.split(" ");
        num1 = Integer.parseInt(tokens[0]);
        num2 = Integer.parseInt(tokens[1]);
    }

    public int doit(String input) {
        Expression expression = null;

        if (input.contains("сложить")) {
            expression = new AddExpression(input);
        } else if (input.contains("умножить")) {
            expression = new MultiplyExpression(input);
        } else if (input.contains("вычесть")) {
            expression = new MinusExpression(input);
        }

        int result = expression.interpret(this);
        System.out.println(input);
        return result;
    }
}

class AddExpression implements Expression {
    private String expression;

    public AddExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public int interpret(InterpreterEngine engine) {
        return engine.add(expression);
    }
}

class MinusExpression implements Expression {
    private String expression;

    public MinusExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public int interpret(InterpreterEngine engine) {
        return engine.minus(expression);
    }
}

class MultiplyExpression implements Expression {
    private String expression;

    public MultiplyExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public int interpret(InterpreterEngine engine) {
        return engine.multiply(expression);
    }
}


class Main {

    public static void main(String[] args) {
        InterpreterEngine engine = new InterpreterEngine();
        System.out.println("РЕЗУЛЬТАТ: " + engine.doit("вычесть 35 и " + engine.doit("умножить 2 и 10")));
        System.out.println("----");
        System.out.println("РЕЗУЛЬТАТ: " + engine.doit("умножить " + engine.doit("сложить 5 и 5") + " и 10"));
    }
}