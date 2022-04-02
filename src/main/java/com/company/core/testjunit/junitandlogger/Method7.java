package com.company.core.testjunit.junitandlogger;


import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

class Method7 {
    public static void main(String[] args) {
        new Method7().run();
    }

    static Logger logger = Logger.getLogger("myXmlLogger");

    private void run() {
        logger.setLevel(Level.ALL);
        Handler handler = null;
        try {
            handler = new FileHandler("src/main/resources/logs/Method7.log");
            logger.addHandler(handler);
            handler.setFormatter(new XMLFormatter());
            handler.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1});
        checkBalance(new int[]{1, 1, 1, 2, 5});
        checkBalance(new int[]{1, 1, 1, 5, 1});
    }

    public boolean checkBalance(int[] arr) {
        boolean result = false;
        if (arr.length < 2) return false;
        int searchValue = Arrays.stream(arr).sum() / 2;

        int sumArray = 0;
        for (int el : arr) {
            sumArray += el;
            if (sumArray == searchValue) result = true;
        }
        logger.info("Дан массив: " + Arrays.toString(arr) + ", результат проверки баланса: " + result);
        return result;
    }
}

