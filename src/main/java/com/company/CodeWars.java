package com.company;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class CodeWars extends Object {


    public static int[] sortArray(int[] array) {
        List l = new ArrayList<Integer>();

        for(int i:array){
            if(i % 2!=0) l.add(i);
        }

        Collections.sort(l);

        for(int i=0; i < array.length;i++){
            if(array[i] % 2==0) continue;
            else{
                array[i] = (int) l.get(0);
                l.remove(0);
            }
        }

        return array;
    }


    public static void main(String[] args) {

        System.out.println(Arrays.toString(sortArray(new int[]{ 5, 3, 2, 8, 1, 4 })));// 1, 3, 2, 8, 5, 4 }



    }


}


