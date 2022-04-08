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

    public static int solveExpression(final String expression) {
        Pattern pattern = Pattern.compile("(-?+[0-9\\?]+)([*+-])(-?+[0-9\\?]+)=(.*)"); // Expr always has form: (num)[op](num)=(num)
        Matcher matcher = pattern.matcher(expression);
        matcher.matches();
        String num1 = matcher.group(1);
        String operator = matcher.group(2);
        String num2 = matcher.group(3);
        String result = matcher.group(4);

        for (int i = 0; i <= 9; i++) {
            int number1 = Integer.parseInt(num1.replaceAll("\\?", i + ""));
            int number2 = Integer.parseInt(num2.replaceAll("\\?", i + ""));
            int numberResult = Integer.parseInt(result.replaceAll("\\?", i + ""));
            //1. check 0xxx
            if(num1.length()!=(number1+"").length() || num2.length()!=(number2+"").length() || result.length()!=(numberResult+"").length()){
                continue;
            }
            //2. check exists i in expression
            if(expression.contains(i+"")) continue;

            int n = 0;
            if (operator.equals("+")) n = number1 + number2;
            if (operator.equals("-")) n = number1 - number2;
            if (operator.equals("*")) n = number1 * number2;
            if (n == numberResult) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------
        System.out.println(solveExpression("19-45=5?"));//-1
        System.out.println(solveExpression("19--45=5?"));//-1
        System.out.println(solveExpression("1--1=?"));//2
        System.out.println(solveExpression("1+1=?"));//2
        System.out.println(solveExpression("123*45?=5?088"));//6
        System.out.println(solveExpression("-5?*-1=5?"));//0


//        assertEquals( "Answer for expression '??*??=302?' " , 5 , Runes.solveExpression("??*??=302?") );
//        assertEquals( "Answer for expression '?*11=??' " , 2 , Runes.solveExpression("?*11=??") );
//        assertEquals( "Answer for expression '??*1=??' " , 2 , Runes.solveExpression("??*1=??") );
//        assertEquals( "Answer for expression '??+??=??' " , -1 , Runes.solveExpression("??+??=??") );

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

}