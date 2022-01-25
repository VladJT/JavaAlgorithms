package com.company.lists;

public interface ILinkedList<E> {

    void insertFirst(E value);

    E removeFirst();

    boolean remove(E value);

    boolean contains(E value);

    boolean isEmpty();

    int size();

    void display();

    E getFirst();

    class Node<E> {
        E item;
        Node next;
        Node prev;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
