package com.company.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MyClass {
    private int number;
    private String name = "MyDefStr";

    public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void printData() {
        System.out.println("number: " + number + ", name: " + name);
    }
}

// name не имеет геттер. Попробуем добраться до private поля name класса MyClass
// number не имеет сеттер
// printData имеет модификатор private !
class Main {

    public static void main(String[] args) {
        MyClass m = new MyClass(1, "FirstClass");
        String name = null;
        try {
            //  getDeclaredFields()возвращает массив полей класса, но теперь и private и protected.
            //  В нашей ситуации можем использовать метод getDeclaredField(String), где String — имя нужного поля.
            Field field = m.getClass().getDeclaredField("name");
            field.setAccessible(true);
            name = (String) field.get(m);

            //На тот случай если у нас вдруг не оказалось setter’a, для установки нового значения полю name можно использовать метод set
            Field fieldNum = m.getClass().getDeclaredField("number");
            fieldNum.setAccessible(true);
            fieldNum.set(m, 155);

            // вызываем private метод
            printData(m);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // рефлексия позволяет создавать экземпляры класса в режиме runtime (во время выполнения программы)!
        // Мы можем создать объект класса по полному имени этого класса. Полное имя класса — это имя класса, учитывая путь к нему в package.
        MyClass myClass = null;
        try {
            Class rntmClass = Class.forName(MyClass.class.getName());
            Class[] params = {int.class, String.class};
            myClass = (MyClass) rntmClass.getConstructor(params).newInstance(12, "SecondClass");
            printData(myClass);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // получаем нужный метод по имени и даем доступ к нему. И для вызова объекта Method используем invoke(Оbject, Args), где Оbject — все также экземпляр класса MyClass
    public static void printData(Object myClass) {
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}