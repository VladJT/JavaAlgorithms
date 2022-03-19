package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static List filterList(final List list) {
        return (List) list.stream()
                .filter(n -> n instanceof Integer)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

        System.out.println(filterList(Arrays.asList(new Object[]{1, "a", "b", "0", 15, 15})));//, true);
        System.out.println(filterList(Arrays.asList(new Object[]{1, 2, "aasf", 1, 123, 123})));//, true);


    }
}
