package com.company.lists;

/**
 * Двухсторонний список позволяет вставлять элементы не только в начало, но и в конец списка.
 * С этой целью для класса списка создается дополнительное поле last.
 */
class TwoSidedLinkedList {
    private Link first;
    private Link last;

    public TwoSidedLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(String name, int age) {
        Link newLink = new Link(name, age);
        if (this.isEmpty())
            last = newLink;
        newLink.next = first;
        first = newLink;
    }

    // В методе delete проверяем список на пустоту, и если следующий элемент после удаляемого равен null,
    // то значение поля last делаем null.
    public Link delete() {
        Link temp = first;
        if (first.next == null)
            last = null;
        first = first.next;
        return temp;

    }

    public void insertLast(String name, int age) {
        Link newLink = new Link(name, age);
        if (this.isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public Link find(String name) {
        Link current = first;
        while (current.name != name) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
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

    public String delete2(){
        Link temp = first;
        if (first.next == null)
            last = null;
        first = first.next;
        return temp.name;
    }



    public static void main(String[] args) {
        TwoSidedLinkedList list = new TwoSidedLinkedList();
        list.insert("Artem", 30);
        list.insert("Misha", 10);
        list.insert("Vova", 5);
        list.insertLast("Petya", 25);

        list.display();
        System.out.println("Удаление элементов списка");

        list.delete("Vova");
        list.display();

    }

}
