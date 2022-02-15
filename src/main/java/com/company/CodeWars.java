package com.company;


import java.math.BigInteger;



class CodeWars extends Object{



    public static BigInteger finance(int n) {
        if(n==0) return new BigInteger("0");
        int sum=0;

        for(int i =0; i<=n;i++) {
            sum += (n+i);
        }

        return finance(n-1).add(BigInteger.valueOf(sum));
    }


    public static void main(String[] args) {

        System.out.println(finance(8));//360
        System.out.println(finance(5));//105


    }


}


