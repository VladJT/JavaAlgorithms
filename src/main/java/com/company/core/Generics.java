package com.company.core;


import java.io.Serializable;

interface Storage<T> {
    void addMoney(T value);

    T getMoney();
}

class Euro {
    float value = 0;

    public Euro(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Euro{" +
                "value=" + value +
                '}';
    }
}

class MoneyStorage<T> implements Storage<T> {

    T money;

    @Override
    public void addMoney(T value) {
        money = value;
    }

    @Override
    public T getMoney() {
        return money;
    }
}

class TestGen<T extends Number, V extends Serializable> {
    private T value;
    protected V value2;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public V getValue2() {
        return value2;
    }

    public void setValue2(V value2) {
        this.value2 = value2;
    }

    public void printInfo() {
        System.out.println("Тип1 - " + value.getClass().getName() + ", тип 2 - " + value2.getClass().getName());
    }

    public boolean equals(TestGen<T, V> o) {
        if (this.value == o.value) return true;
        else return false;
    }

}

class Generics {
    public static void main(String[] args) {
        TestGen<Float, String> t1 = new TestGen<>();
        t1.setValue2("apple");
        t1.setValue(4.0f);
        System.out.println(t1.getValue() + t1.getValue2());
        t1.printInfo();

        System.out.println("-------");

        TestGen<Integer, Long> t2 = new TestGen<>();
        t2.setValue(4);
        t2.setValue2(160L);
        System.out.println(t2.getValue() + t2.getValue2());
        t2.printInfo();
        System.out.println(t1.equals(t2));

        System.out.println("-------");
        Storage<Euro> ms = new MoneyStorage<>();
        ms.addMoney(new Euro(200f));

        System.out.println("ms = " + ms.getMoney());

    }

}
