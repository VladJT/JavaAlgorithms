package com.company.core.game_xo;

// набор цветов для работы с консолью
public class COLORS {
    public static final String SYS = "\u001b[0m";
    public static final String GREY = "\u001b[90m";
    public static final String RED = "\u001b[31m";
    public static final String GREEN = "\u001b[32m";
    public static final String YELLOW = "\u001b[33m";
    public static final String BLUE = "\u001b[34m";


    private static final String MENU_BG = "\u001b[100m";

    public static void setGreyBackgroundColor() {
        System.out.print(SYS+MENU_BG);
    }

    public static void resetColors() {
        System.out.print(SYS);
    }
}
