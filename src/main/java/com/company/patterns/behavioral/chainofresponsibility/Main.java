package com.company.patterns.behavioral.chainofresponsibility;


import java.util.Scanner;

/**
 * <h1>Цепочка обязанностей (Chain of responsibility) </h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐</font>⭐⭐<p></h2>
 * поведенческий паттерн, позволяющий передавать запрос по цепочке потенциальных обработчиков, пока один из них не обработает запрос
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн встречается в Java не так уж часто, так как для его применения нужна цепь объектов, например, связанный список.
 * Область применения цепочки обязанностей — всевозможные обработчики событий, последовательные проверки доступа и прочее.
 * <p>
 * <font color="#fa8e47">Примеры Шаблонных методов в стандартных библиотеках Java:<br></font>
 * java.util.logging.Logger#log()<br>
 * javax.servlet.Filter#doFilter()
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Цепочку обязанностей можно определить по спискам обработчиков или проверок, через которые пропускаются запросы.
 * Особенно если порядок следования обработчиков важен.
 * <p>
 * <font color="#fa8e47">Использование паттерна позволяет:<br></font>
 * <ul>
 * <li>в разрабатываемой системе имеется группа объектов, которые могут обрабатывать сообщения определенного типа</li>
 * <li>все сообщения должны быть обработаны хотя бы одним объектом системы</li>
 * <li>сообщения в системе обрабатываются по схеме «обработай сам либо перешли другому», то есть одни сообщения обрабатываются на том уровне, где они получены,
 * а другие пересылаются объектам иного уровня.</li></ul>
 * <font color="#fa8e47">Важные моменты:<br></font>
 *  <ol>
 * <li>Клиент не знает, какая часть цепочки будет обрабатывать запрос, и он отправит запрос первому объекту в цепочке.</li>
 * <li>Каждый объект в цепочке будет иметь свою собственную реализацию для обработки запроса, полного или частичного, или для отправки его следующему объекту в цепочке.</li>
 * <li>Каждый объект в цепочке должен иметь ссылку на следующий объект в цепочке для пересылки запроса, что достигается с помощью java-композиции</li>
 * <li>Тщательное создание цепочки очень важно, иначе может возникнуть ситуация, когда запрос никогда не будет перенаправлен определенному процессору или в цепочке нет объектов,
 * способных обработать запрос.</li>
 * <li>Шаблон проектирования цепочки ответственности хорош для достижения слабой связи, но он сопряжен с большим количеством классов реализации и проблемами обслуживания,
 * если большая часть кода является общей для всех реализаций.</li></ol>
 */

/*
Банкомат. Пользователь вводит сумму, подлежащую выдаче, и машина выдает сумму в виде определенных валютных купюр, таких как 50$, 20$, 10$ и т.д.
Если пользователь вводит сумму, не кратную 10, возникает ошибка. Мы будем использовать шаблон цепочки ответственности для реализации этого решения.
Цепочка обработает запрос в том же порядке, что и на изображении ниже.
Обратите внимание, что мы можем легко реализовать это решение в одной программе, но тогда сложность возрастет, и решение будет тесно связано.
Поэтому мы создадим цепочку распределительных систем для выдачи купюр по 50, 20 и 10 долларов.
 */

// класс Валюта, в котором будет храниться сумма, подлежащая распределению и используемая реализациями цепочки
class Currency {
    private int amount;

    public Currency(int amt) {
        this.amount = amt;
    }

    public int getAmount() {
        return this.amount;
    }
}

interface DispenseChain {
    void setNextChain(DispenseChain nextChain);//метод для определения следующего процессора в цепочке

    void dispense(Currency cur);//метод, который будет обрабатывать запрос
}

// Поскольку мы разрабатываем нашу систему для работы с тремя типами валютных купюр – 50$, 20$ и 10$, мы создадим 3 конкретные реализации.
class Dollar50Dispenser implements DispenseChain {
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 50) {
            int num = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Выдано 50$ купюр: " + num);
            if (remainder != 0) this.nextChain.dispense(new Currency(remainder));
        } else {
            this.nextChain.dispense(cur);
        }
    }
}

class Dollar20Dispenser implements DispenseChain {
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 20) {
            int num = cur.getAmount() / 20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Выдано 20 купюр: " + num);
            if (remainder != 0) this.nextChain.dispense(new Currency(remainder));
        } else {
            this.nextChain.dispense(cur);
        }
    }

}

class Dollar10Dispenser implements DispenseChain {
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 10) {
            int num = cur.getAmount() / 10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Выдано 10$ купюр: " + num);
            if (remainder != 0) this.nextChain.dispense(new Currency(remainder));
        } else {
            this.nextChain.dispense(cur);
        }
    }

}

// Создание цепочки
class ATMDispenseChain {
    private DispenseChain c1;

    public ATMDispenseChain() {
        // initialize the chain
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public void dispense(Currency c) {
        c1.dispense(c);
    }
}

class Main {
    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        while (true) {
            int amount = 0;
            System.out.println("Введите сумму выдачи($): ");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Сумма должна быть кратна 10$");
            } else {
                atmDispenser.dispense(new Currency(amount));
            }
        }
    }
}
