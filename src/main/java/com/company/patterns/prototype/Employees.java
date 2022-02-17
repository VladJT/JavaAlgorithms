package com.company.patterns.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Прототип</h1>
 * <font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p>
 * порождающий паттерн, который позволяет копировать объекты любой сложности без привязки к их конкретным классам.
 * <p>
 * Шаблон прототипа обычно используется, когда у нас есть экземпляр класса (прототип), и мы хотели бы создать новые объекты, просто скопировав прототип
 * Вместо создания новых объектов нам просто нужно клонировать прототипический экземпляр.
 * <br>
 * Применимость: Паттерн Прототип реализован в базовой библиотеке Java посредством интерфейса Cloneable.
 * Признаки применения паттерна: Прототип легко определяется в коде по наличию методов clone, copy и прочих.
 */

public class Employees implements Cloneable {
    private List<String> empList;

    public Employees() {
        empList = new ArrayList<String>();
    }

    public Employees(List<String> l) {
        empList = l;
    }

    public void loadData() {
        empList.add("Иван");
        empList.add("Стас");
        empList.add("Антон");
        empList.add("Петр");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    protected Employees clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<>();
        for (String s : empList) {
            temp.add(s);
        }
        return new Employees(temp);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Employees etalon = new Employees();
        etalon.loadData();// инициализируем прототип

        Employees c1 = etalon.clone(); // клонируем с прототипа
        List<String> l1 = c1.getEmpList();
        l1.add("Семен");

        Employees c2 = etalon.clone(); // клонируем с прототипа
        List<String> l2 = c2.getEmpList();
        l2.remove(0);
        l2.remove("Антон");

        System.out.println(c1.getEmpList());
        System.out.println(c2.getEmpList());
    }
}


