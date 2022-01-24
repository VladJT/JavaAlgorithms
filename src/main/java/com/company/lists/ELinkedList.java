package com.company.lists;

public class ELinkedList<E> implements ILinkedList<E> {

    protected int size;
    protected Node<E> first;

    @Override
    public void insertFirst(E value) {
//        Node<E> actualFirst = first;
//        Node<E> newFirst = new Node<>(value, actualFirst);
//        this.first = newFirst;
        this.first = new Node<>(value, first);
        size++;
    }

    @Override
    public E removeFirst() {

        if (isEmpty())
            return null;

        Node<E> remNode = first;
        first = remNode.next;
        remNode.next = null;
        size--;

        return remNode.item;
    }

    @Override
    public boolean remove(E value) {

        Node<E> current = first;
        Node<E> prev = null;

        while (current != null) {
            if (current.item.equals(value)) break;
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        } else {
            if (current == first) {
                removeFirst();
                return true;
            }
            prev.next = current.next;
            current.next = null;
            size--;
        }

        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = first;
        while (current != null) {
            if (current.item.equals(value)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        return sb.append("]").toString();
    }

    @Override
    public E getFirst() {
        return first.item;
    }

    public static void main(String[] args) {
        ELinkedList<Integer> l = new ELinkedList<>();
        l.insertFirst(5);
        l.insertFirst(4);
        l.insertFirst(3);
        l.insertFirst(2);
        l.insertFirst(1);

        l.removeFirst();
        l.remove(4);
        l.display();
    }
}
