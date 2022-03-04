package com.company.core.lesson10;

import java.util.ArrayList;

class Fruit {
    private final float weight;
    private String name;

    public Fruit(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}

class Apple extends Fruit {
    public Apple(String name) {
       super(name, 1.0f);
    }
}

class Orange extends Fruit {
    public Orange(String name) {
        super(name, 1.5f);
    }
}

class Box<T extends Fruit> {
    ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void addFruit(T f){
        this.fruits.add(f);
    }
}

class BoxTest {
    public static void main(String[] args) {
        Apple a1 = new Apple("Антоновка");
        Apple a2 = new Apple("Семеринка");
        Apple a3 = new Apple("Белый налив");

        Fruit o1 = new Orange("Марроканский красный");
        Orange o2 = new Orange("Турецкий желтый");

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(a1);
        appleBox.addFruit(a2);
       // appleBox.addFruit(o1);

        Box<Orange> orangeBox = new Box<>();



    }
}
