package com.company.lists;

// односвязный список
public class LinkedList {
    private Link first;

    public LinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(String name, int age) {
        Link newLink = new Link(name, age);
        newLink.next = first;
        first = newLink;
    }

    public Link delete() {
        Link temp = first;
        first = first.next;
        return temp;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }


    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.insert("Artem", 22);
        list.insert("Roman", 31);
        list.insert("Pavel", 34);

        list.display();
    }

}
