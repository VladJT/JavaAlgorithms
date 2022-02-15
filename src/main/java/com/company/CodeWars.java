package com.company;

import com.company.stack_queue.Queue;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.Math.*;


public class CodeWars {


    public static double findUniq(double arr[]) {
       double d=0;

       if(arr[0]==arr[1]) d = arr[0];
       if(arr[0]==arr[2]) d = arr[0];
       if(arr[1]==arr[2]) d = arr[1];

        for(double x: arr){
            if (x!=d) return x;        }

        return -1;
    }


    public static void main(String[] args) {

        System.out.println(findUniq(new double[]{0, 1, 0}));

    }


}


