package com.company;

class Singleton {

    private static Singleton singleton;

    public static Singleton getInstance() {

        if (singleton == null) {

            singleton = new Singleton();

        }

        return singleton;

    }

}
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


