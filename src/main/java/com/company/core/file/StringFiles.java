package com.company.core.file;


import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

class StringFiles {


    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/readme.txt");

//        if (file.exists()) {
//            file.delete();
//        } else {
//            file.createNewFile();
//        }

        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));

        System.out.println(new String(is.readAllBytes()));

        int size = is.available();//размер всех символов в потоке

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(is.read());
        }
        System.out.println(sb);

        File input = new File("src/main/resources/input.txt");
        File output = new File("src/main/resources/output.txt");
        Scanner scanner = new Scanner(input);
        PrintWriter pw = new PrintWriter(output);
        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();
        pw.print(a.add(b));
        pw.close();
        scanner.close();
    }


}
