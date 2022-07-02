package com.company;


class CodeWars {


    public static String[] process(String shape) {
        // complete me!
        return shape.split("\n");
    }

    private static void printArray(String arr[]) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------

        String shape = String.join("\n", new String[]
                {"+------------+",
                        "|            |",
                        "|            |",
                        "|            |",
                        "+------+-----+",
                        "|      |     |",
                        "|      |     |",
                        "+------+-----+"});


        String actual[] = process(shape);
        printArray(actual);

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }


}


