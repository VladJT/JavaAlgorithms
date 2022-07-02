package com.company.patterns.behavioral.strategy;


import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Стратегия (Strategy)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐⭐</font></h2>
 * поведенческий паттерн, выносит набор алгоритмов в собственные классы и делает их взаимозаменимыми
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Стратегия часто используется в Java-коде, особенно там, где нужно подменять алгоритм во время выполнения программы.
 * Начиная с Java 8, многие примеры стратегии можно заменить простыми lambda-выражениями.
 * <p>
 * <font color="#fa8e47">Примеры Шаблонных методов в стандартных библиотеках Java:<br></font>
 * java.util.Comparator#compare(), вызываемые из Collections#sort().<br>
 * javax.servlet.http.HttpServlet: метод service(), а также все методы doXXX() принимают объекты HttpServletRequest и HttpServletResponse в параметрах.
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Класс делегирует выполнение вложенному объекту абстрактного типа или интерфейса.
 * <p>
 * <font color="#fa8e47">Мотивы:<br></font>
 * <ul>
 * <li>Программа должна обеспечивать различные варианты алгоритма или поведения</li>
 * <li>Нужно изменять поведение каждого экземпляра класса</li>
 * <li>Необходимо изменять поведение объектов на стадии выполнения</li>
 * <li>Введение интерфейса позволяет классам-клиентам ничего не знать о классах, реализующих этот интерфейс и инкапсулирующих в себе конкретные алгоритмы</li>
 * </ul>
 * <font color="#fa8e47">Способ решения:<br></font>
 * Отделение процедуры выбора алгоритма от его реализации. Это позволяет сделать выбор на основании контекста.
 * <p>
 * <font color="#fa8e47">Участники:<br></font>
 * <font color="#1E90FF">Класс Strategy</font> определяет, как будут использоваться различные алгоритмы.<br>
 * <font color="#1E90FF">Конкретные классы ConcreteStrategy</font> реализуют эти различные алгоритмы.<br>
 * <font color="#1E90FF">Класс Context</font> использует конкретные классы ConcreteStrategy посредством ссылки на конкретный тип абстрактного класса Strategy.
 * Классы Strategy и Context взаимодействуют с целью реализации выбранного алгоритма (в некоторых случаях классу Strategy требуется посылать запросы классу Context).
 * Класс Context пересылает классу Strategy запрос, поступивший от его класса-клиента.
 * <p>
 * <font color="fa8eff">и стратегия, и декоратор могут применяться для изменения поведения конкретных классов.<br>
 * Достоинство стратегии в том, что интерфейс кастомизации не совпадает с публичным интерфейсом и может быть куда более удобным.<br>
 * Недостаток стратегии в том, что для использования стратегии необходимо изначально проектировать класс с возможностью регистрации стратегий.</font>
 */


//В нашем примере мы попытаемся реализовать простую корзину покупок, в которой у нас есть две стратегии оплаты — с помощью кредитной карты или PayPal.
interface PaymentStrategy {
    public void pay(int amount);
}

// реализация оплаты кредиткой
class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate) {
        this.name = nm;
        this.cardNumber = ccNum;
        this.cvv = cvv;
        this.dateOfExpiry = expiryDate;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit/debit card");
    }

}

// реализация оплаты через Paypal
class PaypalStrategy implements PaymentStrategy {
    private String emailId;
    private String password;

    public PaypalStrategy(String email, String pwd) {
        this.emailId = email;
        this.password = pwd;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }
}

// товар
class Item {
    private String upcCode;
    private int price;

    public Item(String upc, int cost) {
        this.upcCode = upc;
        this.price = cost;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }

}

// мы можем реализовать корзину покупок, а способ оплаты потребует ввода в качестве стратегии оплаты
class ShoppingCart {
    //List of items
    List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public int calculateTotal() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(PaymentStrategy paymentMethod) {
        int amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}


class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item("Сок", 10);
        Item item2 = new Item("Печенье", 40);
        cart.addItem(item1);
        cart.addItem(item2);

        //pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

        //pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }
}
