package com.company.lists;

// односвязный список
public class LinkedList {
    private Link first;

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public LinkIterator getIterator(){
        return new LinkIterator(this);
    }


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

    /**
     * поиск элемента в списке
     *
     * @param name - ФИО для поиска
     * @return
     */
    public Link find(String name) {
        Link current = first;
        while (current != null) {
            if (current.name == name) return current;
            else current = current.next;
        }
        return null;
    }

    public Link delete(String name) {
        Link current = first;
        Link previous = first;
        while (current.name != name) {
            if (current.next == null)
                return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == first)
            first = first.next;
        else
            previous.next = current.next;
        return current;
    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert("Artem", 22);
        list.insert("Roman", 31);
        list.insert("Pavel", 34);

        Link l = list.find("Roman");
        if (l != null) l.display();
        else System.out.println("Элемент Roman не найден");

        l = list.find("Stas");
        if (l != null) l.display();
        else System.out.println("Элемент Stas не найден");

        list.delete("Roman");


        list.display();
    }

}
