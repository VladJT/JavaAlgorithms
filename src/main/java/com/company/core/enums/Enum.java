package com.company.core.enums;

class Enum {
    enum Fruit {
        ORANGE("Апельсин", 3), APPLE("Яблоко", 3), BANANA("Банан", 2), CHERRY("Вишня", 1);
        private String russianTitle;
        private int weight;

        public String getRussianTitle() {
            return russianTitle;
        }

        public int getWeight() {
            return weight;
        }

        Fruit(String russianTitle, int weight) {
            this.russianTitle = russianTitle;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        for (Fruit fruit : Fruit.values()) {
            System.out.printf("Средний вес фрукта %s составляет: %d ед.\n", fruit.getRussianTitle(), fruit.getWeight());
        }
    }
}
