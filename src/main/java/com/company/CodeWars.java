package com.company;

public class CodeWars {

    public static int getCount(String str) {
        int vowelsCount=0;
        char[] cArray = str.toCharArray();
        for(char c: cArray){
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') vowelsCount++;
        }
        return vowelsCount;
    }


    public static void main(String[] args) {

        System.out.println(getCount("abracadabra"));
    }
}
