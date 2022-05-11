package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


class CodeWars {

    public static class SecureList {
        int[] arr;

        public SecureList(int[] ints) {
            arr = ints;
        }

        public String get(int i) {
            String s = arr[i] + "";
            int[] newArr = new int[arr.length - 1];
            System.arraycopy(arr, 0, newArr, 0, i);
            System.arraycopy(arr, i + 1, newArr, i, arr.length - i - 1);
            arr = newArr;
            return s;
        }

        public int size() {
            return arr.length;
        }

        @Override
        public String toString() {
            int[] newArr = arr.clone();
            arr = new int[0];
            return Arrays.toString(newArr).replaceAll(" ","");
        }
    }

    public static String[] process(String shape) {
        String[] sArr = shape.split("\n");
        char[][] arr = new char[sArr.length][sArr[0].length()];
        for (int i = 0; i < sArr.length; i++) {
            for (int j = 0; j < sArr[0].length(); j++) {
                arr[i][j] = sArr[i].charAt(j);
            }
        }

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < sArr.length; i++) {
            for (int j = 0; j < sArr[0].length(); j++) {
                if (arr[i][j] == '+') {
                    String s = getFigure(arr, i, j);
                    if (s != null) result.add(s);
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }

    private static String getFigure(char[][] arr, int i, int j) {
        if (j + 1 < arr[j].length && arr[i][j + 1] == '-' && i + 1 < arr.length && arr[i + 1][j] == '|') {
            int start_i = i;
            int start_j = j;
            StringBuilder sb = new StringBuilder();
            while (true) {

            }

        } else return null;
    }


    static String shape = String.join("\n", new String[]
            {"+------------+",
                    "|            |",
                    "|            |",
                    "|            |",
                    "+------+-----+",
                    "|      |     |",
                    "|      |     |",
                    "+------+-----+"});


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------
        // String actual[] = process(shape);
        //  printArray(actual);
        SecureList secureList = new SecureList(new int[]{1, 2, 3, 4});
        System.out.println(secureList.get(1)); // prints 2
        System.out.println(secureList);        // prints [1,3,4]
        System.out.println(secureList);        // prints [1,3,4]
        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

    private static void printArray(String[] actual) {
        for (String s : actual) {
            System.out.println(s);
        }
    }


}


