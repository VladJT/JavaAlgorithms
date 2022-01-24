package com.company.lists;

public class EStack<E> {

    private final ELinkedList<E> data;

    public EStack() {
        this.data = new ELinkedList<>();
    }

    public void push(E value) {
        data.insertFirst(value);
    }

    public E pop() {
        return data.removeFirst();
    }

    public E peek() {
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean isFull() {
        return false;
    }

    public void display() {
        data.display();
    }

    public String toString() {
        return data.toString();
    }

    public static void main(String[] args) {
        EStack<String> s = new EStack<>();

        s.push("c");
        s.push("b");
        s.push("a");
        s.pop();
        s.display();

    }
}
