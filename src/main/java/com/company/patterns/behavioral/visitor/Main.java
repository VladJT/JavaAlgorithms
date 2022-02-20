package com.company.patterns.behavioral.visitor;


/**
 * <h1>Посетитель (visitor)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐⭐</font><br>
 * <font color="#fa8e47">Популярность:⭐</font>⭐⭐<p></h2>
 * поведенческий паттерн, который позволяет добавить новую операцию для целой иерархии классов, не изменяя код этих классов
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Над каждым объектом некоторой структуры выполняется одна или более операций. Нужно определить новую операцию, не изменяя классы объектов.
 * <p>
 * <font color="#fa8e47">Примеры в стандартных библиотеках Java:<br></font>
 * javax.lang.model.element.AnnotationValue и AnnotationValueVisitor<br>
 * javax.lang.model.element.Element и ElementVisitor
 * <p>
 * <font color="#fa8e47">Используйте паттерн состояние в следующих случаях:</font>
 * <ul><li>имеются различные объекты разных классов с разными интерфейсами, но над ними нужно совершать операции, зависящие от конкретных классов;</li>
 * <li>необходимо над структурой выполнить различные, усложняющие структуру операции;</li>
 * <li>часто добавляются новые операции над структурой</li></ul>
 * <font color="ffcc00">Важно! Посетитель нечасто встречается в Java-коде из-за своей сложности и нюансов реализазации.</font>
 */


/*
Шаблон посетителя используется, когда нам нужно выполнить операцию над группой объектов аналогичного типа.
С помощью шаблона посетителя мы можем переместить операционную логику из объектов в другой класс.
Например, подумайте о корзине покупок, куда мы можем добавлять различные типы товаров (элементов).
Когда мы нажимаем на кнопку оформить заказ, он вычисляет общую сумму, подлежащую оплате.
Теперь мы можем иметь логику вычисления в классах элементов или перенести эту логику в другой класс, используя шаблон посетителя.
 */

interface Item {
    public int accept(ShoppingCartVisitor visitor);// с помощью Посетителя мы можем прикрутить любое поведение к этой иерархии (с оговоркой, что в ней будет реализован метод accept).
}

class Book implements Item {
    private int price;
    private String isbnNumber;

    public Book(int cost, String isbn) {
        this.price = cost;
        this.isbnNumber = isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

class Fruit implements Item {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int priceKg, int wt, String nm) {
        this.pricePerKg = priceKg;
        this.weight = wt;
        this.name = nm;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }


    public int getWeight() {
        return weight;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

}

//У нас есть метод visit() для различных типов элементов в интерфейсе посетителя, который будет реализован конкретным классом посетителя.
interface ShoppingCartVisitor {
    int visit(Book book);

    int visit(Fruit fruit);
}

class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        System.out.println("Книга ISBN::" + book.getIsbnNumber() + " цена = " + cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " цена = " + cost);
        return cost;
    }
}

class Main {
    public static void main(String[] args) {
        Item[] items = new Item[]{new Book(800, "Ведьмак"), new Book(650, "Гарри Поттер"),
                new Fruit(70, 2, "Банан"), new Fruit(90, 5, "Яблоко")};
        int total = calculatePrice(items);
        System.out.println("Общая стоимость = " + total);
    }

    private static int calculatePrice(Item[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (Item item : items) {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
