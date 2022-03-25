package com.company;

import java.util.Arrays;

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


    public static int peakHeight(char[][] mountain) {

        int counter = 0;
        char charMountain = 'x';



        while (NeedCount(mountain)) {
            counter++;
            for (int i = 0; i < mountain.length; i++) {
                for (int j = 0; j < mountain[0].length; j++) {
                    System.out.print(mountain[i][j]);
                    if (mountain[i][j] != '^') continue;
                    if (checkNeedPrintHeight(mountain, i, j, charMountain)) mountain[i][j] = charMountain;
                }
                System.out.println();
            }//for i
            System.out.println();
            charMountain = (charMountain == 'x') ? 'V' : 'x';
        }

        for (int i = 0; i < mountain.length; i++) {
            for (int j = 0; j < mountain[0].length; j++) {
                System.out.print(mountain[i][j]);
            }
            System.out.println();
        }//for i
        return counter;
    }

    private static boolean checkNeedPrintHeight(char[][] mountain, int i, int j, char charMountain) {
        if (i == 0 || i == (mountain.length - 1) || j == 0 || j == (mountain[0].length - 1)) return true;

        String st = "" + mountain[i - 1][j] + mountain[i + 1][j] + mountain[i][j - 1] + mountain[i][j + 1];
        st = st.replaceAll("\\^", "").replaceAll(charMountain + "", "");

        if (st.length() == 0)
            return false;

        return true;
    }

    private static boolean NeedCount(char[][] mountain) {
        for (int i = 0; i < mountain.length; i++) {
            for (int j = 0; j < mountain[0].length; j++) {
                if (mountain[i][j] == '^') return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] mountain = {
                "^^^^^^        ".toCharArray(),
                " ^^^^^^^^     ".toCharArray(),
                "  ^^^^^^^     ".toCharArray(),
                "  ^^^^^       ".toCharArray(),
                "  ^^^^^^^^^^^ ".toCharArray(),
                "  ^^^^^^      ".toCharArray(),
                "  ^^^^        ".toCharArray()
        };
        System.out.println("height = " + peakHeight(mountain));//3
    }
}
