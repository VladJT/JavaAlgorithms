package com.company.lists;

/**
 * Двусвязный список
 *
 * @param <E> - тип хранимого значения в Node
 */
public class EDeque<E> extends ETwoSideLinkedList<E> {

    @Override
    public void insertFirst(E value) {
        Node<E> actualFirst = first;
        Node<E> newFirst = new Node<>(value, actualFirst);
        if (first != null)
            actualFirst.prev = newFirst;
        this.first = newFirst;
        size++;
        if (size == 1) {
            last = first;
        }
    }

    @Override
    public void insertLast(E value) {
        if (isEmpty()) insertFirst(value);
        else {
            Node<E> newNode = new Node<>(value, last, null);
            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = first;
        Node<E> prev = null;
        Node<E> next = null;

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

            next = current.next;
            prev.next = next;
            if (next != null) {
                next.prev = prev;
            }

            current.next = null;
            current.prev = null;
            size--;
        }
        return true;
    }

    @Override
    public E removeFirst() {
        E remValue = super.removeFirst();
        if (!isEmpty()) {
            first.prev = null;
        }
        return remValue;
    }

    public void showFromHead() {
        super.display();
    }

    public void showFromLast() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = last;
        while (current != null) {
            sb.append(current.item);
            if (current.prev != null) sb.append(" -> ");
            current = current.prev;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        EDeque<Integer> d = new EDeque<>();
        d.insertFirst(5);
        d.insertFirst(4);
        d.insertFirst(3);
        d.insertFirst(2);
        d.insertFirst(1);
        d.insertLast(6);
        d.remove(1);

        d.showFromHead();
        d.showFromLast();

        d.getIterator().reset();
        System.out.println("\ncurrent item for iterator: " + d.getIterator().getCurrent().item);
        System.out.println("Вывод через foreach:");
        for (Integer j : d) {
            System.out.print(j);
            if (d.getIterator().hasNext()) System.out.print(", ");
        }
    }
}
