package com.company.patterns.structural.decorator;

/**
 * <h1>Декоратор (Decorator)</h1>
 * <h2>
 * <font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * структурный паттерн, который позволяет добавлять объектам новые поведения на лету, помещая их в объекты-обёртки.<br>
 * Декоратор позволяет оборачивать объекты бесчисленное количество раз благодаря тому, что и обёртки, и реальные оборачиваемые объекты имеют общий интерфейс.
 * Шаблон Декоратор предоставляет гибкую альтернативу практике создания подклассов с целью расширения функциональности.
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн можно часто встретить в Java-коде, особенно в коде, работающем с потоками данных.
 * <p>
 * <font color="#fa8e47">Примеры Декораторов в стандартных библиотеках Java:<br></font>
 * Все подклассы java.io.InputStream, OutputStream, Reader и Writer имеют конструктор, принимающий объекты этих же классов.
 * <p>
 * <font color="#fa8e47">Используйте Декоратор:<br></font>
 * <li>Чтобы добавлять обязанности к отдельным объектам динамически и прозрачно, то есть не затрагивая другие объекты.</li>
 * <li>Для обязанностей, которые могут быть сняты</li>
 * <li>Когда расширение с помощью подклассов нецелесообразно. Иногда возможно большое количество независимых расширений,
 * что приведет к взрыву подклассов для поддержки каждой комбинации. Или определение класса может быть скрыто или иным образом недоступно для подклассов.</li>
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Декоратор можно распознать по создающим методам, которые принимают в параметрах объекты того же абстрактного типа или интерфейса, что и текущий класс.
 *  <p>
 * <font color="fa8eff">1. Класс-декоратор должен быть того же типа, что и декорируемый класс, — реализовывать тот же интерфейс или наследовать тот же базовый класс.<br>
 * 2. Декоратор реализует поведение исходного класса; при этом не изменяет его, а добавляет своё до или после вызова базового поведения.<br>
 * 3. Это достигается за счёт того, что декоратор содержит в себе объект базового класса и вызывает его методы там, где требуется дополнить поведение.</font>
 */

//  интерфейс Car
interface Car {
    public int getSpeed();

    public int getBaggageWeight();
}

//У нас есть обычный автомобиль - класс SimpleCar
class SimpleCar implements Car {
    private int speed = 50;
    private int baggageWeight = 100;

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getBaggageWeight() {
        return this.baggageWeight;
    }
}

/*
Чтобы сделать из простого автомобиля скоростной спортивный автомобиль у нас есть класс-декоратор SportCar,
который в конструкторе принимает класс SimpleCar и добавляет скорости обычному автомобилю.
 */
class SportCar implements Car {
    private Car car;
    public int boostSpeed = 200;

    public SportCar(Car car) {
        this.car = car;
    }

    @Override
    public int getSpeed() {
        return this.car.getSpeed() + boostSpeed;
    }

    @Override
    public int getBaggageWeight() {
        return this.car.getBaggageWeight() - 20;
    }
}

//Также чтобы увеличить грузоподъемность простого автомобиля у нас есть класс-декоратор Truck,
// который в конструкторе принимает класс SimpleCar и добавляет грузоподъемности обычному автомобилю.
class Truck implements Car {
    private Car car;
    public int addedWeight = 1000;

    public Truck(Car car) {
        this.car = car;
    }

    @Override
    public int getSpeed() {
        return this.car.getSpeed();
    }

    @Override
    public int getBaggageWeight() {
        return this.car.getBaggageWeight() + addedWeight;
    }
}


class Main {
    public static void main(String[] args) {
        Car simpleCar = new SimpleCar();

        //Используем обычный автомобиль SimpleCar, превращая его с помощью декораторов в спортивный автомобиль или грузовик.
        Car sportCar = new SportCar(simpleCar);
        System.out.println("Скорость спортивного авто: " + sportCar.getSpeed());
        System.out.println("Грузоподьемность: " + sportCar.getBaggageWeight());

        Car truck = new Truck(simpleCar);
        System.out.println("Скорость грузового авто:  " + truck.getSpeed());
        System.out.println("Грузоподьемность: " + truck.getBaggageWeight());
    }
}
