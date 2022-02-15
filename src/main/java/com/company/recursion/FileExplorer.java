package com.company.recursion;

import java.io.File;

class FileExplorer {

    public static void main(String[] args) {
        File file = new File("D:\\Projects\\Git2\\");
        viewFiles(file, 0);
    }

    private static void viewFiles(File file, int level) {
        StringBuilder prefix = new StringBuilder().append("\t".repeat(level));
        if (file.isFile()) {
            System.out.println(prefix + "💾 " + file.getName());
        } else {
            System.out.println(prefix + "📁 " + file.getName());
            level++;
            for (File listFile : file.listFiles()) {
                viewFiles(listFile, level);
            }
        }


    }

}
