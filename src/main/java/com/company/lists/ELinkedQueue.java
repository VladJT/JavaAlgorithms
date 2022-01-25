package com.company.lists;

public class ELinkedQueue<E> {
    private final ETwoSideLinkedList<E> data;

    public ELinkedQueue() {
        data = new ETwoSideLinkedList<E>();
    }

    public boolean insert(E value) {
        data.insertLast(value);
        return true;
    }

    public E remove() {
        return data.removeFirst();
    }

    public E peekFront() {
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void display() {
        data.display();
    }


    public static void main(String[] args) {
        ELinkedQueue<Integer> q = new ELinkedQueue<>();
        q.insert(1);
        q.insert(2);
        q.insert(3);
        q.insert(4);
        q.insert(5);
        System.out.println("Front: " + q.peekFront());
        q.remove();
        q.display();
    }

}
