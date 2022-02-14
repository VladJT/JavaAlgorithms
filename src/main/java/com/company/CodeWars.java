package com.company;

import com.company.stack_queue.Queue;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.Math.*;


public class CodeWars {


    public static String decode(String r) {
        String numString = "";
        String rezString = "";

        for (char c : r.toCharArray()) {
            if (c >= '0' && c <= '9') numString += c;
            else break;
        }

        int num = Integer.parseInt(numString);
        r = r.substring(numString.length());

        if (num % 2 == 0 || num % 13 == 0) return "Impossible to decode";

        for (char c : r.toCharArray()) {
            boolean isExists = false;
            int cCode = c - 'a';
            for (char i = 'a'; i <= 'z'; i++) {
                int iCode = i - 'a';
                if (iCode * num % 26 == cCode) {
             //       System.out.println(c+" "+(char)i+ "   "+cCode+" - "+iCode);
                    rezString += (char) i;
                    isExists = true;
                    break;
                }
            }
        }//for

        return rezString;
    }

    public static void main(String[] args) {
        //testing_decode("1273409kuqhkoynvvknsdwljantzkpnmfgf", "uogbucwnddunktsjfanzlurnyxmx");

        System.out.println(decode("5057aan"));//uogbucwnddunktsjfanzlurnyxmx
        System.out.println("---");
        System.out.println(decode("6015ekx"));
        System.out.println("---");
        System.out.println(decode("105860ymmgegeeiwaigsqkcaeguicc"));
//        System.out.println(decode("1122305vvkhrrcsyfkvejxjfvafzwpsdqgp"));




    }


}


