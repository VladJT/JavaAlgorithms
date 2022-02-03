package com.company;

import java.util.Arrays;
import java.util.StringJoiner;

public class CodeWars {


    public static String formatWords(String[] words) {
        if(words == null) return "";

        StringJoiner sb = new StringJoiner(", ");
        for (String w : words) {
            if(!w.isEmpty()) sb.add(w);
        }
        return sb.toString().replaceAll(", (\\S+)", " and $1");

    }



    public static void main(String[] args) {
//[gbg, tp, hpzrpxd, iltxe, hhb, pwsk, hc{xnkk{] and nsgfaxuwy
        System.out.println(formatWords((new String[]{"[gbg", "tp", "hc{xnkk{]","nsgfaxuwy"})));

    }
}
