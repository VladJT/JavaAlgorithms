package com.company.core.lesson10;

import java.util.ArrayList;
import java.util.List;


class Fruit {
    private float weight;
    private String name;

    public Fruit(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
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
    private ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }


    public void addFruits(T... f) {
        this.fruits.addAll(List.of(f));
    }

    // высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
    public float getWeight() {
        if (!fruits.isEmpty()) return fruits.size() * fruits.get(0).getWeight();
        return 0f;
    }

    //метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра.
    // true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
    public boolean compare(Box<?> box1) {
        if (this.getWeight() == box1.getWeight()) return true;
        else return false;
    }

    public int addFruitsFromAnotherBox(Box<T> box1) {
        int size = box1.fruits.size();
        this.fruits.addAll(box1.fruits);
        box1.fruits.clear();
        return size;
    }

    public void printInfo(String boxName) {
        System.out.println(boxName + " " + this + " весит: " + this.getWeight());
    }

    @Override
    public String toString() {
        return fruits.toString();
    }
}

class BoxTest {
    public static void main(String[] args) {
        Apple[] apples = {new Apple("Семеринка"), new Apple("Белый налив"), new Apple("Антоновка")};
        Orange[] oranges = {new Orange("Марроканский красный апельсин"), new Orange("Турецкий желтый апельсин")};

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruits(apples[0], apples[1]);
        appleBox.printInfo("Коробка с яблоками №1");

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruits(oranges[0], oranges[1]);
        orangeBox.printInfo("Коробка с апельсинами");

        System.out.println("Коробка с апельсинами и Коробка с яблоками №1 равны по весу? " + orangeBox.compare(appleBox));
        System.out.println("------");

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addFruits(apples[2]);
        appleBox2.printInfo("Коробка с яблоками №2");


        System.out.println("Пересыпаем яблоки из appleBox в appleBox2. Пересыпано = " + appleBox2.addFruitsFromAnotherBox(appleBox));
        System.out.println("------");
        appleBox.printInfo("Коробка с яблоками №1");
        appleBox2.printInfo("Коробка с яблоками №2");

        System.out.println("Коробка с апельсинами и Коробка с яблоками №2 равны по весу? " + orangeBox.compare(appleBox2));
    }
}
