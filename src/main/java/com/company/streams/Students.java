package com.company.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class Students {

    static class Student {
        private String name;
        private int gpa;
        private int age;

        public Student(int age, int gpa, String name) {
            this.name = name;
            this.gpa = gpa;
            this.age = age;
        }

        int getGpa() {
            return gpa;
        }

        int getAge() {
            return age;
        }

        String getFullName() {
            return name;
        }
    }

    public static String sort(List<Student> students) {
        return students.stream()
                .sorted(sortOrder)
                .map(Student::getFullName)
                .collect(Collectors.joining(","));
    }

    // Получив список студентов, отсортируйте их по (от наиболее важных к наименее важным):
    //Средний балл (по убыванию)
    //Первая буква фамилии (по возрастанию)
    //Возраст (по возрастанию)
    static final Comparator<Student> sortOrder = Comparator
            .comparingInt(Student::getGpa).reversed()
            .thenComparing(s -> s.getFullName().split(" ")[1].charAt(0))
            .thenComparingInt(Student::getAge);


    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(23, 88, "David Goodman"));
        students.add(new Student(25, 82, "Mark Rose"));
        students.add(new Student(22, 90, "Jane Boe"));
        students.add(new Student(25, 90, "Jane Dane"));
        // assertEquals("Jane Boe,Jane Dane,David Goodman,Mark Rose",
        System.out.println(sort(students));

    }

}
