package com.company.sort;

public class SpeedTest {

    public static  long time;

    public static void startTime(){
        time = System.currentTimeMillis();
    }

    public static void endTime(){
        time = System.currentTimeMillis() - time;
        System.out.println("Прошло милисекунд: "+time);
    }
}
