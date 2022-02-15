package com.company.lists;

/**
 * итераторы содержат следующие методы:
 * reset() — перемещение в начало списка;
 * nextLink() — перемещение итератора к следующему элементу;
 * getCurrent() — получение элемента, на который указывает итератор;
 * atEnd() — возвращает true, если итератор находится в конце списка;
 * insertAfter() — вставка элемента после итератора;
 * insertBefore() — вставка элемента до итератора;
 * deleteCurrent() — удаление элемента в текущей позиции итератора.
 */
class LinkIterator {
    private Link current;
    private Link previous;
    private LinkedList list;

    public LinkIterator(LinkedList list) {
        this.list = list;
        this.reset();
    }

    public void reset() {
        current = list.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return (current.next == null);
    }

    public void nextLink() {
        previous = current;
        current = current.next;
    }

    public Link getCurrent() {
        return current;
    }

    public void insert(String name, int age) {
        Link newLink = new Link(name, age);
        newLink.next = this.list.getFirst();
        this.list.setFirst(newLink);
    }

    public void insertAfter(String name, int age) {
        Link newLink = new Link(name, age);
        if (list.isEmpty()) {
            list.setFirst(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
    }

    public void insertBefore(String name, int age) {
        Link newLink = new Link(name, age);
        if (previous == null) {
            newLink.next = list.getFirst();
            list.setFirst(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    public String deleteCurrent() {
        String name = current.name;
        if (previous == null) {
            list.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }

        return name;
    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkIterator i = list.getIterator();

        i.insert("Artem", 22);
        i.insert("Roman", 31);
        i.insert("Pavel", 34);

        i.reset();
        i.insertBefore("Stas", 40);
        while(!i.atEnd()){
            i.nextLink();
        }
        i.getCurrent().display();
        // itr.previous;


        // itr.insertAfter("Artem", 20);
        //     itr.insertBefore("Sergey", 10);

        System.out.println("all list:");
        list.display();
    }


}
