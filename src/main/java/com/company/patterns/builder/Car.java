package com.company.patterns.builder;

/**
 * Строитель (англ. Builder) — порождающий шаблон проектирования предоставляет способ создания составного объекта.
 * Этот шаблон рекомендуется использовать, когда класс имеет большое количество однотипных параметров и трудно запомнить их порядок и назначение.
 * <p>
 * Преимущества использования паттерна Builder:
 * - дает больший контроль над процессом создания объектов;
 * - позволяет варьировать внутреннее представление объекта;
 * - отделяет процесс конструирования объекта от его внутреннего представления
 * <p>
 * несколько слов о разнице между паттерном Factory и паттерном Строитель. Factory сосредотачивается на том, что именно создается, а Builder - на том, как оно создается.
 */

// TODO Реализация Builder через статический внутренний класс
class Car {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;

    private Car(Builder builder) {
        a = builder.a;
        b = builder.b;
        c = builder.c;
        d = builder.d;
        e = builder.e;
        f = builder.f;
    }

    @Override
    public String toString() {
        return "Car{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                '}';
    }



    //Реализация Builder через статический внутренний класс
    static class Builder {
        //Обязательные параметры
        private final int a;
        private final int b;
        //Необязательные параметры (со значениями по умолчанию)
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;

        //Конструктор с обязательными параметрами
        public Builder(int a, int b) {
            this.a = a;
            this.b = b;
        }

        //Методы с возвращающим типом Builder для необязательного параметра с, d, e, f,
        public Builder c(int value) {
            c = value;
            return this;
        }
        public Builder d(int value) {
            d = value;
            return this;
        }
        public Builder e(int value) {
            e = value;
            return this;
        }
        public Builder f(int value) {
            f = value;
            return this;
        }

        //Метод для генерации объекта
        public Car build() {
            return new Car(this);
        }
    }// ....... Builder ..........




    //Теперь в методе main при создании объекта вызывается конструктор статического класса Builder с обязательными параметрами.
    // Затем через точку вызываются все необходимые необязательные параметры.
    // В завершение вызывается метод build() для генерации объекта.
    public static void main(String[] args) {
        Car car = new Car.Builder(1, 2)
                .c(23)
                .d(52)
                .build();
        System.out.println(car.toString());
    }
}

