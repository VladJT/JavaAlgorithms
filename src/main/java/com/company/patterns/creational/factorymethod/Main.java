package com.company.patterns.creational.factorymethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <h1>Фабричный метод (Factory Method)</h1>
 * <font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐⭐</font><p>
 * порождающий шаблон проектирования, предоставляющий подклассам интерфейс для создания экземпляров некоторого класса.<br>
 * Задача паттерна «Фабричный метод» — перемещение создания экземпляров в подклассы
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн можно часто встретить в любом Java-коде, где требуется гибкость при создании продуктов.
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font> Фабричный метод можно определить по создающим методам,
 * которые возвращают объекты продуктов через абстрактные типы или интерфейсы.
 * Это позволяет переопределять типы создаваемых продуктов в подклассах.
 * <p>
 * <font color="#fa8e47">Достоинства:<br></font>
 * позволяет сделать код создания объектов более универсальным, не привязываясь к конкретным классам (ConcreteProduct),
 * а оперируя лишь общим интерфейсом (Product);<br>
 * позволяет установить связь между параллельными иерархиями классов.<br>
 * <font color="#fa8e47">Недостатки:<br></font>
 * необходимость создавать наследника Creator для каждого нового типа продукта (ConcreteProduct).
 */

/*
Представьте, что вы создаете модуль игровых наград. Первая версия вашего приложения может обрабатывать только награду ЗОЛОТО,
поэтому основная часть вашего кода находится внутри класса GoldReward.
Через некоторое время ваша игра становится довольно популярной.
Каждый день вы получаете десятки запросов от игроков о добавлении новой валюты GEM в приложение.
 */

// Создали базовый интерфейс IGameItem
interface GameItem {
    void open();
}

class GoldReward implements GameItem {
    @Override
    public void open() {
        System.out.println("🪙 Gold chest open");
    }
}

class GemReward implements GameItem {
    @Override
    public void open() {
        System.out.println("💎 Gem chest open");
    }
}

// создаем ItemGenerator, который будет открывать награды и создавать их
abstract class ItemGenerator {
    public abstract GameItem createItem();// factoryMethod
}

// Давайте создадим конкретные реализации ItemGenerator
class GoldGenerator extends ItemGenerator {
    @Override
    public GameItem createItem() {
        return new GoldReward();
    }
}

class GemGenerator extends ItemGenerator {
    @Override
    public GameItem createItem() {
        return new GemReward();
    }
}

class Main {
    public static void main(String[] args) {
        List<ItemGenerator> generatorList = new ArrayList<>();
        generatorList.add(new GemGenerator());
        generatorList.add(new GoldGenerator());

        for (int i = 0; i < 10; i++) {
            int idx = new Random().nextInt(2);// 0 .. 1
            GameItem newReward = generatorList.get(idx).createItem();
            newReward.open();
        }
    }
}
