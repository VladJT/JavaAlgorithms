package com.company.stack_queue;

public class Queue {
    /**
     * В качестве очереди будем использовать массив.
     * Переменная front — маркер начала очереди.
     * Rear — маркер конца.
     * Items — количество элементов в очереди.
     */
    private int maxSize;
    private int[] queue;
    private int front;
    private int rear;
    private int items;

    public Queue(int s) {
        maxSize = s;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
        items = 0;
    }

    public void insert(int i) {
        if (rear == maxSize - 1)
            rear = -1;
        queue[++rear] = i;
        items++;
    }

    public int remove() {
        int temp = queue[front++];
        if (front == maxSize)
            front = 0;
        items--;
        return temp;
    }

    public int peek() {
        return queue[front];
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public boolean isFull() {
        return (items == maxSize);
    }

    public int size() {
        return items;
    }

    public static void main(String[] args) {
        Queue q = new Queue(3);
        q.insert(1);
        q.insert(2);
        q.insert(3);
        q.remove();

        System.out.println(q.isFull());
        System.out.println(q.peek());


        System.out.println(q.front);
        System.out.println(q.rear);
    }

}
