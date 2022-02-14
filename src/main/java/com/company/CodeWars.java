package com.company;

import com.company.stack_queue.Queue;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.Math.*;


public class CodeWars {

    /**
     * encode("mer", 6015)  -->  "6015ekx"
     * <p>
     * m --> 12,   12 * 6015 % 26 = 4,    4  --> e
     * e --> 4,     4 * 6015 % 26 = 10,   10 --> k
     * r --> 17,   17 * 6015 % 26 = 23,   23 --> x
     * <p>
     * So we get "ekx", hence the output is "6015ekx"
     */
    public static String decode(String r) {
        String numString = "";
        String rezString = "";

        for (char c : r.toCharArray()) {
            if (c >= '0' && c <= '9') numString += c;
            else break;
        }

        int num = Integer.parseInt(numString);
        r = r.substring(numString.length());

        for (char c : r.toCharArray()) {
            int charS = c-'a';
            int newS = charS * num % 26;
            char decodedChar = (char)('a'+newS);
            rezString += decodedChar;
        }


        return rezString;
    }

    public static void main(String[] args) {
        //testing_decode("1273409kuqhkoynvvknsdwljantzkpnmfgf", "uogbucwnddunktsjfanzlurnyxmx");
        System.out.println(decode("6015ekx"));
        System.out.println(decode("1273409kuqhkoynvvknsdwljantzkpnmfgf"));


    }


}


