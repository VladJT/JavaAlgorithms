package com.company.core.lesson11;

import java.util.TreeMap;

class PhoneBook {
    private TreeMap<String, String> book;

    public PhoneBook() {
        book = new TreeMap<>();
    }

    public void add(String name, String newPhone) {
        if (!book.containsKey(name)) {
            book.put(name, newPhone);
            System.out.println("В Справочник добавлена запись: [" + name + " " + newPhone + "]");
        } else {
            String phoneInBook = book.get(name);
            if (phoneInBook.contains(newPhone)) {
                System.out.println("Телефон [" + newPhone + "] уже присутствует в Справочнике у человека " + name);
            } else {
                book.remove(name);
                book.put(name, phoneInBook + ", " + newPhone);
                System.out.println("Добавлен телефон [" + newPhone + "] в запись: [" + name + " " + phoneInBook + "]");
            }
        }
    }

    public String get(String name) {
        if (book.containsKey(name)) {
            return book.get(name);
        }
        return "Нет человека с такой фамилией в Справочнике";
    }

    public void printBook() {
        book.forEach((s, s2) -> System.out.printf("%10s:\t %s\n", s, s2));
    }

}


class PhoneBookTest {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "546-905");
        book.add("Иванов", "+7(911)345-8899");
        book.add("Петров", "123-5567");
        book.add("Иванов", "546-905"); // дубль телефона, не надо добавлять
        book.add("Сидоров", "445-532");

        System.out.println("\n\n---ТЕСТИРУЕМ ПОИСК---");
        System.out.println("Ищем по фамилии Иванов: " + book.get("Иванов"));
        System.out.println("Ищем по фамилии Васин: " + book.get("Васин"));

        System.out.println("\n\n--ПЕЧАТАЕМ СПРАВОЧНИК--");
        book.printBook();
    }
}
