package com.company.core.lesson10;

class A {
    public void showA() {
        System.out.println("a");
    }
}

class B extends A {
    public void showB() {
        System.out.println("b");
    }
}

class C extends B {
    public void showC() {
        System.out.println("c");
    }
}

class TestGen<T extends A> {
    T value;

    public TestGen() {
        this.value = (T) new Object();
    }
}

class Test {
    public static void main(String[] args) {

        TestGen<A> a = new TestGen<>();
        TestGen<? super B> b = new TestGen<B>();// ограничиваем нижнюю границу
        TestGen<C> c = new TestGen<>();

    }
}
