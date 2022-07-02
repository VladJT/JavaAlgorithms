package com.company.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class PicsFiles {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/pics.txt");

        var pics = new String(new FileInputStream(file).readAllBytes()).split("&+");

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Введите № картинки (1-3): ");
            System.out.println(pics[in.nextInt() - 1]);
        }

    }
}
