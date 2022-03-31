package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class CodeWars2 {


//    static int find(String needle, String haystack) {
//        String regSt = Arrays.stream(needle.split("\\$"))
//                .collect(Collectors.joining("[$]"))
//                .replaceAll("_", ".");
//
//        Pattern pattern = Pattern.compile(regSt);
//        Matcher matcher = pattern.matcher(haystack);
//        if (matcher.find()) {
//            return matcher.start();
//        } else return -1;
//    }


    //GPA (descending)
//First letter of last name (ascending)
//Age (ascending)
//    public static String sort2(List<Student> students) {
//        return students.stream()
//                .sorted(new Comparator<Student>() {
//                    @Override
//                    public int compare(Student o1, Student o2) {
//                        if (o1.getGpa() != o2.getGpa()) return o2.getGpa() - o1.getGpa();
//
//                        String lastName1 = o1.getFullName().split(" ")[1];
//                        String lastName2 = o2.getFullName().split(" ")[1];
//
//                        if (lastName1.charAt(0) != lastName2.charAt(0))
//                            return lastName1.charAt(0) - lastName2.charAt(0);
//                        return o1.getAge() - o2.getAge();
//                    }
//                })
//                .map(s -> s.getFullName())
//                .collect(Collectors.joining(","));
//    }


    public static String fixCutPaste(String text) {
        System.out.println(text);
        String[] s = text.split(" ");

        String result = "";
        int i = 0;
        while (i < s.length) {
            String mask = s[i];
            if (Arrays.stream(s).filter(x -> x.equals(mask)).count() == 1) {
                result += s[i] + " ";
                i++;
                continue;
            }

            int dupIndex = 0;
            for (int j = i + 1; j < s.length; j++) {
                if (s[i].equals(s[j])) {
                    dupIndex = j;
                    break;
                }
            }

            int length = dupIndex - i;
            boolean isDuplicate = true;
            for (int x = 0; x < length; x++) {
                if (!s[i + x].equals(s[dupIndex + x])) isDuplicate = false;
            }
            if (isDuplicate) {
                for (int x = 0; x < length; x++) {
                    result += s[i + x] + " ";
                }
                i = dupIndex+length-1;
            } else {
                result += s[i] + " ";
                i++;
            }


//            int counter = 0;
//            while (true) {
//                if (dupIndex + counter >= s.length) break;
//                if (!s[i + counter].equals(s[dupIndex + counter])){
//                    break;
//                }
//                counter++;
//            }
//
//            if (i + counter == dupIndex) {
//                for (int x = 0; x < counter; x++) {
//                    result += s[i + x] + " ";
//                }
//                i = dupIndex + counter;
//            }


        }//while


        return result.trim();
    }

    public static void main(String[] args) {
        String in = "Here is some piece of text piece of text that was was accidentally double double pasted.";
        String out = "Here is some piece of text that was accidentally double pasted.";
           System.out.println(fixCutPaste(in));
        System.out.println(fixCutPaste("REPEATED REPEATED REPEATED words at the start"));
    }


}