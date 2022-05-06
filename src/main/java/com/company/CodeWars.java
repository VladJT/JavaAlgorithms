package com.company;


import java.util.ArrayList;


class CodeWars {


    public static String[] process(String shape) {
        String[] sArr = shape.split("\n");
        char[][] arr = new char[sArr.length][sArr[0].length()];
        for(int i=0;i< sArr.length;i++){
            for(int j=0;j<sArr[0].length();j++){
                arr[i][j] = sArr[i].charAt(j);
            }
        }

        ArrayList<String> result = new ArrayList<>();

        for(int i=0;i< sArr.length;i++) {
            for (int j = 0; j < sArr[0].length(); j++) {
                if(arr[i][j]=='+'){
                    String s = getFigure(arr, i,j);
                    if(s!=null) result.add(s);
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }

    private static String getFigure(char[][] arr, int i, int j) {
        if(j+1<arr[j].length && arr[i][j+1]=='-' && i+1<arr.length && arr[i+1][j]=='|'){
            int start_i = i;
            int start_j = j;
            StringBuilder sb = new StringBuilder();
            while(true){

            }

        }else return null;
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
        String actual[] = process(shape);
        printArray(actual);
        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

    private static void printArray(String[] actual) {
        for (String s : actual) {
            System.out.println(s);
        }
    }


}


