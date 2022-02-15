package com.company.lists;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class ELinkedList<E> implements ILinkedList<E>, Iterable<E> {
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

    public Node<E> getFirstNode() {
        return first;
    }

    @Override
    public Iterator<E> iterator() {
        return iterator;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    EListIterator<E> iterator = new EListIterator<E>(this);

    public EListIterator<E> getIterator() {
        return iterator;
    }


    /**
     * ИТЕРАТОР
     *
     * @param <E> - тип для связанного списка
     */
    public class EListIterator<E> implements Iterator<E> {
        private Node<E> current;
        private Node<E> previous;

        private ELinkedList<E> list;

        public EListIterator(ELinkedList<E> list) {
            this.list = list;
            this.reset();
        }

        public void reset() {
            current = list.getFirstNode();
            previous = null;
        }

        public boolean atEnd() {
            return (current.next == null);
        }

        public void nextLink() {
            previous = current;
            current = current.next;
        }

        public Node<E> getCurrent() {
            return current;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E item = current.item;
            nextLink();
            return item;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

    public static void main(String[] args) {
        ELinkedList<Integer> l = new ELinkedList<>();
        l.insertFirst(5);
        l.insertFirst(4);
        l.insertFirst(3);
        l.insertFirst(2);
        l.insertFirst(1);
        l.display();

        l.getIterator().reset();
        System.out.println("\ncurrent item for iterator: " + l.getIterator().getCurrent().item);
        System.out.println("Вывод через foreach:");
        for (Integer j : l) {
            if (l.getIterator().hasNext())
                System.out.print(j + " -> ");
            else System.out.print(j + " ");
        }
    }
}
