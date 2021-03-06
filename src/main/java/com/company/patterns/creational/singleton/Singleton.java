package com.company.patterns.creational.singleton;

/**
 * <h1>Одиночка (Singletone)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * порождающий паттерн, который гарантирует существование только одного объекта определённого класса,
 * а также позволяет достучаться до этого объекта из любого места программы.
 * <p>
 * реализовать Одиночку очень просто — достаточно скрыть конструктор и предоставить статический создающий метод.
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:</font> Одиночку можно определить по статическому создающему методу, который возвращает один и тот же объект.
 * <p>
 * Одиночке нашлось применение в стандартных библиотеках Java:
 * <ul>
 * <li>java.lang.Runtime#getRuntime()</li>
 * <li>java.awt.Desktop#getDesktop()</li>
 * <li>java.lang.System#getSecurityManager()</li></ul>
 * <p>
 * Одиночка имеет такие же преимущества и недостатки, что и глобальные переменные.
 * Его невероятно удобно использовать, но он нарушает модульность вашего кода.
 * <p>
 * Тот же класс ведёт себя неправильно в многопоточной среде. Несколько потоков могут одновременно
 * вызвать метод получения Одиночки и создать сразу несколько экземпляров объекта.
 * Чтобы исправить проблему, требуется синхронизировать потоки при создании объекта-Одиночки.
 */


class Singleton {
    private String name = "";
    private static Singleton instance;

    public String getName() {
        return name;
    }

    private Singleton(String name) {
        System.out.println("вызов конструктора");
        this.name = name;
    }

    public static Singleton getInstance(String name) {
        if (instance == null) {
            instance = new Singleton(name);
        }
        return instance;
    }
}

/**
 * Вариант 2. Double Checked Locking & volatile
 * + Ленивая инициализация
 * + Высокая производительность
 * - Поддерживается только с JDK 1.5 [5]
 */
class Singleton2 {
    // volatile означает:
    // Она всегда будет атомарно читаться и записываться. Даже если это 64-битные double или long.
    // Java-машина не будет помещать ее в кэш. Так что ситуация, когда 10 потоков работают со своими локальными копиями исключена.
    private static volatile Singleton2 instance;

    public static Singleton2 getInstance() {
        Singleton2 localInstance = instance;

        if (localInstance == null) {
            // Synchronized - это ключевое слово, которое позволяет заблокировать доступ к методу или части кода, если его уже использует другой поток.
            synchronized (Singleton2.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Singleton2();
                }
            }
        }
        return localInstance;
    }
}

class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance("first");
        Singleton s2 = Singleton.getInstance("second try (not used)");

        System.out.println(s1.getName() + " = " + s2.getName());
        System.out.println(s1.hashCode() + " = " + s2.hashCode());

    }
}