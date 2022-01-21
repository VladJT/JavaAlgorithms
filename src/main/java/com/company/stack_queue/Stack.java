package com.company.stack_queue;

public class Stack {
    /**
     * В поле maxSize хранится значение максимального размера стека.
     * Поле top — это вершина стека. Это позиция элемента, который «последним вошел — первым вышел».
     * Значение поля top «-1» говорит о том, что стек пустой.
     * В качестве структуры данных, в которой будет храниться стек, используется массив.
     */
    private int maxSize;
    private int[] stack;
    private int top;


    public Stack(int size) {
        this.maxSize = size;
        this.stack = new int[this.maxSize];
        this.top = -1;
    }

    // Каждый новый элемент добавляют в конец стека
    public void push(int i) {
        this.stack[++this.top] = i;
    }

    //Чтобы убрать элемент из стека, реализуем метод pop. Он удаляет элемент из стека, который находится в позиции top.
    public int pop() {
        return this.stack[this.top--];
    }

    // Для получения элемента стека, который находится в позиции top, реализуем метод peek
    public int peek() {
        return this.stack[this.top];
    }

    public boolean isEmpty() {
        return (this.top == -1);
    }

    public boolean isFull() {
        return (this.top == this.maxSize - 1);
    }

    public static void main(String[] args) {
        Stack s = new Stack(3);
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.isFull());
        System.out.println(s.peek());
        s.pop();
        s.push(5);
        System.out.println(s.peek());
    }
}
