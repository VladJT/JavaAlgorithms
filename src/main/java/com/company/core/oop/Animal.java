package com.company.core.oop;


public abstract class Animal {
    private String name;
    protected final int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }



    public abstract void voice();
}


class Cat extends Animal {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cat(String name, String color, int age) {
        super(name, age);
        this.color = color;
    }

    @Override
    public void voice() {
        System.out.println("Meow");
    }
}



class PersianCat extends Cat {

    public PersianCat(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public void voice() {
        System.out.println("HREEW");
    }
}



class Main {
    public static void main(String[] args) {

        Animal[] a = new Animal[3];
       // a[0] = new Animal("Оззи");
        a[1] = new Cat("Мусик", "Серый" , 7);
        a[2] = new PersianCat("Pers", "ss", 5);

        //a[0].voice();
        a[1].voice();
        a[2].voice();

        Cat c = new Cat("Mu","Red", 7);

    }
}
