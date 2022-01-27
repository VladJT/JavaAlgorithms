package com.company.recursion;

import java.util.LinkedHashSet;
import java.util.Set;


public class Anagram {


    private Set<String> result = new LinkedHashSet<>();
    private char[] chars;


    public static void main(String[] args) {
        System.out.println(new Anagram().findAll("abbat"));
    }

    private Set<String> findAll(String word) {
        result.clear();
        chars = word.toCharArray();

        find(chars.length);
        return result;
    }

    private void find(int length) {
        if (length == 1) {
            return;
        }
        for (int i = 0; i < length; i++) {
            find(length - 1);
            result.add(String.valueOf(chars));
            rotate(length);
        }

    }

    private void rotate(int length) {
        int first = chars.length - length;
        char temp = chars[first];
        for (int i = first + 1; i < chars.length; i++) {
            chars[i - 1] = chars[i];
        }
        chars[chars.length - 1] = temp;

    }
}
