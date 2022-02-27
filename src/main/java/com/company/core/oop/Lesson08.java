package com.company.core.oop;


import com.company.core.Color;

import javax.naming.BinaryRefAddr;

interface DDD{
     abstract void show(int x);
}

@FunctionalInterface
interface DDD2{
    abstract void show(int x, int y);
}

interface Runner{
    int i = 10;
    void run(int a);
}

interface Alive extends Flying, Swiming, Runner{

}

interface Flying{
     void fly();
}

interface Swiming{
    void swim();
}

class Duck implements Alive{

    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }

    @Override
    public void run(int z) {
        System.out.println("run");
    }
}


class MyCat{
    private String name;
    private Color color;
    Bird b;


    public MyCat(String name, Color color) {
        this.name = name;
        this.color = color;

        b= new Bird();
    }

    public MyCat() {

    }

    public Color getColor() {
        return color;
    }

    class Bird{
        int age;
    }

    public void getInfo(){

    }
}



class Lesson08 {
    public static void main(String[] args) {
//        Runner r = new Duck();
//        r.run();
//
        MyCat c = new MyCat("d",Color.RED);
        System.out.println(c.getColor());
        System.out.println(c.getClass());

        MyCat cat  = new MyCat("d",Color.BLACK){
            int age= 10;

            public void getInfo(){
                System.out.println("111");
            }
        };

        cat.getInfo();
        System.out.println(cat.getClass());

        Runner r1 = (a) ->{   System.out.println("cool="+ a);
        };
        r1.run(2);
        System.out.println(r1.getClass());


        DDD d = System.out::println;

        d.show(1);
        System.out.println(d.getClass());

        DDD2 d2 = (x,y)->{
            System.out.println(x+" + "+y+" = "+(x+y));

        };


        d2.show(4,8);

    }


}
