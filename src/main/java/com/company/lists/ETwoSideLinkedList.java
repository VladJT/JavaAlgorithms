package com.company.lists;

/**
 * LinkedList с указателем на первый и последний элементы
 * first + last
 *
 * @param <E>
 */
class ETwoSideLinkedList<E> extends ELinkedList<E> {

    protected Node<E> last;

    public void insertFirst(E value) {
        super.insertFirst(value);
        if (size == 1) {
            last = first;
        }
    }

    public void insertLast(E value) {
        if (isEmpty()) insertFirst(value);
        else {
            Node<E> newNode = new Node<>(value, null);
            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    @Override
    public E removeFirst() {
        E remValue = super.removeFirst();
        if (isEmpty()) {
            last = null;
        }
        return remValue;
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
            if (current == last) {
                last = prev;
                last.next = null;
            }
            prev.next = current.next;
            current.next = null;
            size--;
        }

        return true;
    }

    public E getLast() {
        return last.item;
    }

    public static void main(String[] args) {
        ETwoSideLinkedList<Integer> l = new ETwoSideLinkedList<>();
        l.insertFirst(5);
        l.insertFirst(4);
        l.insertFirst(3);
        l.insertFirst(2);
        l.insertFirst(1);
        l.insertLast(8);
        l.remove(3);

        System.out.println("contains 2? - " + l.contains(2));
        System.out.println("contains 3? - " + l.contains(3));
        l.display();
    }

}
