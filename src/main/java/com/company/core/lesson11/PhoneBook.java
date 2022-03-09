package com.company.core.lesson11;

import java.util.HashMap;
import java.util.TreeMap;

class PhoneBook {
    private TreeMap<String, String> book;

    public PhoneBook() {
        book = new TreeMap<>();
    }

    public void add(String name, String phone) {
        if (!book.containsKey(name)) {
            book.put(name, phone);
            System.out.println("Добавлена запись: " + name + " " + phone);
        } else {
            String phoneInBook = book.get(name);
            if (phoneInBook.contains(phone)) {
                System.out.println("Телефон " + phone + " уже есть в базе у человека " + name);
            } else {
                phoneInBook += ", " + phone;
                book.remove(name);
                book.put(name, phoneInBook);
                System.out.println("Добавлена запись: " + name + " " + phoneInBook);
            }
        }
    }

    public String get(String name) {
        if (book.containsKey(name)) {
            return book.get(name);
        }
        return "Нет человека с такой фамилией в базе";
    }

}


class PhoneBookTest {


    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "546-905");
        book.add("Иванов", "+7(911)345-8899");
        book.add("Петров", "123-5567");
        book.add("Иванов", "546-905");
        book.add("Сидоров", "445-532");

        System.out.println("--------");
        System.out.println("Ищем по фамилии Иванов: " + book.get("Иванов"));
        System.out.println("Ищем по фамилии Сидоров: " + book.get("Сидоров"));
        System.out.println("Ищем по фамилии Васин: " + book.get("Васин"));
    }
}
