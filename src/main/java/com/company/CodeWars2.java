package com.company;

import java.util.ArrayList;
import java.util.Arrays;

class CodeWars2 {


    public static String compress(int[] _raw) {
        int[] raw = Arrays.copyOf(_raw, _raw.length + 1);
        raw[raw.length - 1] = -1;

        String rez = "";

        int curStep = 0, nextStep = 0;
        int stepCount = 1;
        int countDup = 1;

        int closeState = 0;

        for (int i = 0; i < raw.length - 1; i++) {

            if (raw[i] == raw[i + 1]) {
                countDup++;
                continue;
            }
            if (countDup > 1) {
                rez += raw[i] + "*" + countDup + ",";
                countDup = 1;
                closeState = 1;
                continue;
            }

            curStep = (i == 0) ? 0 : raw[i] - raw[i - 1];
            if (closeState == 1) {
                curStep = 0;
                closeState = 0;
            }
            nextStep = (i == raw.length - 1) ? 0 : raw[i + 1] - raw[i];

            if (curStep == nextStep) {
                stepCount++;
                continue;
            }

            if (stepCount > 1) {
                rez = rez.substring(0, rez.length() - 1);//удаляем ,
                if (Math.abs(curStep) == 1) rez += "-" + raw[i] + ",";
                else rez += "-" + raw[i] + "/" + Math.abs(curStep) + ",";
                stepCount = 1;
                closeState = 1;
            } else {
                rez += raw[i] + ",";
            }

        }


        return (rez.substring(0, rez.length() - 1));
    }

    static int[] sequence(int x) {
        int[] arr = new int[x];
        for(int i =0;i<arr.length;i++)
            arr[i]=i+1;

        Object[] l=  Arrays.stream(arr).mapToObj(n->n+"").sorted().toArray();
        for(int i =0;i<arr.length;i++)
            arr[i]=Integer.parseInt(l[i]+"");
        return arr;
    }

    public static void main(String[] args) {
//        System.out.println(compress(new int[]{1, 1, 2, 3, 4, 5, 7, 9}));//expected:<1*2[,2-5,7,9]> but was:<1*2[-5-9/2]>
//        System.out.println(compress(new int[]{1, 3, 4, 5, 7}));//"1,3-5,7")
//        System.out.println(compress(new int[]{1, 2, 2, 3}));//"1,2*2,3"
//        System.out.println(compress(new int[]{1, 2, 3}));//"1,2,3
//        System.out.println(compress(new int[]{1, 10, 8, 6, 7}));//1,10-6/2,7

        System.out.println(Arrays.toString(sequence(9)));
    }
}
