package com.company;

import java.awt.List;
import java.util.*;
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


    public static String translate(String speech, String[] vocabulary) {
        ArrayList<String> vocabularyList = new ArrayList(Arrays.asList(vocabulary));
        String[] speechArr = speech.split(" ");

        int index = 0;
        while (index < speechArr.length) {
            String regSt = speechArr[index].replaceAll("[,?.!]", "").replaceAll("\\*", ".");

            int curIndexInVoc = 0, count = 0;
            for (int i = 0; i < vocabularyList.size(); i++) {
                if (vocabularyList.get(i).matches(regSt) && vocabularyList.get(i).length() == regSt.length()) {
                    count++;
                    curIndexInVoc = i;
                }
            }

            // если найдено 100% одно совпадение
            if (count == 1) {
                String newWord = speechArr[index].replace(speechArr[index].replaceAll("[,?.!]", ""),vocabularyList.get(curIndexInVoc));

                speech = " "+speech+" ";
                speech = speech.replace(" "+speechArr[index]+" "," "+newWord+" ").trim();

                speechArr[index] = vocabularyList.get(curIndexInVoc);
                vocabularyList.remove(curIndexInVoc);
                // speechArr.remove(index);
                index = 0;// начинаем поиск с начала
                continue;
            }
            index++;
        }


        return speech;
    }


    public static void main(String[] args) {
        System.out.println(translate("*** **** **s *****n, f** **e *r* m***!", new String[]{"mmy", "name", "iss", "legion", "for", "wwe", "are", "many"}));//"mmy name iss legion, for wwe are many!", *** **** **s *****n, f** **e *r* m***!", new String[]{"mmy", "name", "iss", "legion", "for", "wwe", "are", "many"}));
        System.out.println(translate("a**? *c*. **e,", new String[]{"ace", "acd", "abd"}));//abd? acd. ace,
    }
}
