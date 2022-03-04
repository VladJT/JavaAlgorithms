package com.company.core.lesson10;

import java.util.ArrayList;

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

    public void addFruit(T f) {
        this.fruits.add(f);
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

    public int addFruitsFromAnotherBox(Box<T> box1){
        int size = box1.fruits.size();
        this.fruits.addAll(box1.fruits);
        box1.fruits.clear();
        return size;
    }


}

class BoxTest {
    public static void main(String[] args) {
        Apple a1 = new Apple("Антоновка");
        Apple a2 = new Apple("Семеринка");
        Apple a3 = new Apple("Белый налив");

        Orange o1 = new Orange("Марроканский красный");
        Orange o2 = new Orange("Турецкий желтый");

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(a1);
        appleBox.addFruit(a2);
        System.out.println("Коробка с appleBox весит: " + appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(o1);
        orangeBox.addFruit(o2);
        System.out.println("Коробка с orangeBox весит: " + orangeBox.getWeight());

        System.out.println("Коробки orangeBox и appleBox равны по весу? "+orangeBox.compare(appleBox));

        System.out.println("------");

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addFruit(a3);
        System.out.println("Коробка с appleBox2 весит: " + appleBox2.getWeight());

        System.out.println("Пересыпаем яблоки из appleBox в appleBox2. Пересыпано = "+ appleBox2.addFruitsFromAnotherBox(appleBox));
        System.out.println("Коробка с appleBox весит: " + appleBox.getWeight());
        System.out.println("Коробка с appleBox2 весит: " + appleBox2.getWeight());

        System.out.println("Коробки orangeBox и appleBox2 равны по весу? "+orangeBox.compare(appleBox2));

    }
}
