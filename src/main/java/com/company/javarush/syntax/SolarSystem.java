package com.company.javarush.syntax;

class SolarSystem {

    public static long age = 4_568_200_000L;

    public static int planetsCount = 8;

    public static int starsCount = 1;

    public static String starName = "Солнце";

    public static int galacticCenterDistance = 27170;


    public static void main(String[] args) {


        System.out.println("Человечество живет в Солнечной системе.");
        System.out.println("Ее возраст около " + age + " лет.");
        System.out.println("В Солнечной системе " + planetsCount + " известных планет.");
        System.out.println("Как и большинство звездных систем, состоит из " + starsCount + " звезды.");
        System.out.println("Звезды по имени " + starName + ".");
        System.out.println("Расстояние к центру галактики составляет " + galacticCenterDistance + " световых лет.");
        System.out.println("Каждый обитатель Солнечной системы должен знать эту информацию!");

    }
}
