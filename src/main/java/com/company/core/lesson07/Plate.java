package com.company.core.lesson07;

class Plate {
    private int foodCount;
    private final int maxFoodCount = 10;

    public Plate(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public boolean isEmpty() {
        return (foodCount == 0);
    }

    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Тарелка с едой = " + foodCount;
    }

    public int decreaseFood(int catEatFoodCount) {
        int countEat;
        if (catEatFoodCount > foodCount) {
            countEat = foodCount;
        } else {
            countEat = catEatFoodCount;
        }
        foodCount -= countEat;
        return countEat;
    }

    // наполнить миску
    public void fill() {
        this.foodCount = maxFoodCount;
    }
}
