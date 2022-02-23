package com.company.core;


import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Strings {

    public static void main(String[] args) {
        String s1 = "  ";
        String s2 = """
                this
                is
                string
                too
                """;
        String s3 = new String(new byte[]{65,66,67});
        String s4 = new String("Catab");
        System.out.println(s4.startsWith("Ca"));



        System.out.println(s1.isEmpty());// проверка на ""
        System.out.println(s1.isBlank());// проверка на "   "
        System.out.println("    обрезка пробелов   ".trim());

        String s5 = "Скажи-ка, 567 дядя, ведь +не    456     даром";
        for(String st: s5.split("\s+\\d{3}")){
            System.out.println(st.repeat(3));
        }

        // проверка на регулярное выражение
        System.out.println(Pattern.matches("А.+а","Алла"));//true

        // проверка на регулярное выражение
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher("vlad@mail.ru");
        System.out.println(matcher.find());//true


        String msgStr="/msg Влад какое-то сообщение";
        if (msgStr.startsWith("/msg")) {
            String[] msgArr = msgStr.split("\s+", 3);
            System.out.println("Автор: "+msgArr[1]);
            System.out.println("Сообщение: "+msgArr[2]);
        }



    }



}
