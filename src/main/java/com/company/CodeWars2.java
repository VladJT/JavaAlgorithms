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


    public static int[] distributionOf(int[] golds) {
        System.out.println(Arrays.toString(golds));
        int[] rez = {0, 0};

        int i = 0;
        int j = golds.length - 1;

        int whoTakeGold = 1;

        while (i <= j) {
            whoTakeGold = (whoTakeGold == 0) ? 1 : 0;

            if (i == j) {
                rez[whoTakeGold] += golds[i];
                break;
            }

            if ((j - i) == 2) {
                if (golds[i] >= golds[j]) {
                    rez[whoTakeGold] += golds[i++];
                } else {
                    rez[whoTakeGold] += golds[j--];
                }
                continue;
            }

            if ((golds[i] + golds[j - 1]) > (golds[i + 1] + golds[j])) {
                rez[whoTakeGold] += golds[i];
                i++;
            } else {
                rez[whoTakeGold] += golds[j];
                j--;
            }

        }
        return rez;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(distributionOf(new int[]{10, 1000, 2, 1})));//1001, 12
        System.out.println(Arrays.toString(distributionOf(new int[]{208, 407, 928, 368, 806, 628, 824, 969, 239})));//
    }


}