package com.company.core.lesson07;

class Cat {
    private String name;
    private int maxAppetite;
    private int curAppetite;

    public Cat(String name, int maxAppetite) {
        this.name = name;
        this.maxAppetite = maxAppetite;
        this.curAppetite = this.maxAppetite;
    }


    public void eat(Plate plate) {
        int eatCount = plate.decreaseFood(curAppetite);
        curAppetite -= eatCount;
        System.out.println("Поел кот " + this);
    }


    public boolean isHungry() {
        return (curAppetite != 0);
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return name + ", макс. аппетит = " + maxAppetite + ", текущий аппетит = " + curAppetite;
    }
}
