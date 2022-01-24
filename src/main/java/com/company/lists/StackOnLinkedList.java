package com.company.lists;

// Реализация стека на базе связного списка
public class StackOnLinkedList {
    private LinkedList list;

    public StackOnLinkedList() {
        list = new LinkedList();
    }

    public void push(String name, int age) {
        list.insert(name, age);
    }

    public String pop() {
        return list.delete().name;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        list.display();
    }

    public static void main(String[] args) {
        StackOnLinkedList sl = new StackOnLinkedList();
        sl.push("Artem", 30);
        sl.push("Viktor", 20);
        sl.push("Sergey", 10);
        sl.display();
        while (!sl.isEmpty()) {
            System.out.println("Элемент " + sl.pop() + " удален из стека");
        }
    }
}
