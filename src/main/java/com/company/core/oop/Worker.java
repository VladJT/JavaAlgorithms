package com.company.core.oop;

import java.sql.SQLOutput;

public class Worker {
    private String name;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Worker(String name, String position, String email, String phone, double salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void show() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d c з/п %.2f [%s, %s]", position, name, age, salary, phone, email);
    }


    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        workers[0] = new Worker("Дмитрий", "Инженер", "dim@mail.com", "89234646456", 80000, 58);
        workers[1] = new Worker("Степан", "Ст.механик", "sss@rambler.com", "894645662", 47000, 35);
        workers[2] = new Worker("Иван", "Прораб", "iva@mailbox.com", "89235675752", 98000, 34);
        workers[3] = new Worker("Андрей", "Инженер", "andrey@outlook.com", "892786555", 49000, 41);
        workers[4] = new Worker("Семен", "Ст.механик", "semen@mail.com", "89215556787", 55000, 43);

        for (Worker w : workers) {
            if (w.getAge() > 40) w.show();
        }
    }
}