package com.company.lists;

public class QueueOnLinkedList {
    private TwoSidedLinkedList queue;

    public QueueOnLinkedList() {
        queue = new TwoSidedLinkedList();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(String name, int age) {
        queue.insertLast(name, age);
    }

    public String delete() {
        return queue.delete2();
    }

    public void display() {
        queue.display();
    }

    public static void main(String[] args) {
        QueueOnLinkedList q = new QueueOnLinkedList();
        q.insert("Artem", 30);
        q.insert("Viktor", 20);
        q.insert("Sergey", 10);
        q.display();
        while (!q.isEmpty()) {
            System.out.println("Элемент " + q.delete() + " удален из очереди");
        }
    }

}
