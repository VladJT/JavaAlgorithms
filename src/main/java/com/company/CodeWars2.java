package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class CodeWars2 {


    public static String formatDuration(int seconds) {
        System.out.println(seconds);
        if (seconds == 0) return "now";

        long mSec = seconds;

        long years = mSec / (3600 * 24 * 365);
        mSec = mSec - years * (3600 * 24 * 365);
        long days = mSec / (3600 * 24);
        mSec = mSec - days*(3600 * 24);
        long hour = mSec / 3600, min = mSec / 60 % 60, sec = mSec % 60;



        StringBuilder sb = new StringBuilder();
        if (years > 0) sb.append(years).append(" year").append(years > 1 ? "s" : "").append(", ");
        if (days > 0) sb.append(days).append(" day").append(days > 1 ? "s" : "").append(", ");
        if (hour > 0) sb.append(hour).append(" hour").append(hour > 1 ? "s" : "").append(", ");
        if (min > 0) sb.append(min).append(" minute").append(min > 1 ? "s" : "").append(", ");
        if (sec > 0) sb.append(sec).append(" second").append(sec > 1 ? "s" : "").append(", ");

        String result = sb.toString();
        result = result.substring(0,result.lastIndexOf(", "));

        if(result.contains(", ")){
            result = result.replaceAll(",\\s(\\d+)\\s(\\w+)$", " and $1 $2");
        }

        return result;
    }


    public static void main(String[] args) {

        System.out.println(formatDuration(1));//"1 second"
        System.out.println(formatDuration(62));//"1 minute and 2 seconds",
        System.out.println(formatDuration(3662));//1 hour, 1 minute and 2 seconds
    }

}