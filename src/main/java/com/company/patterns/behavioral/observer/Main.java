package com.company.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Наблюдатель (Observer)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐⭐</font><p></h2>
 * поведенческий паттерн, который позволяет объектам оповещать другие объекты об изменениях своего состояния<br>
 * Еще этот паттерн называют: Dependents (подчиненные) или Publish-Subscribe (издатель — подписчик). <br>
 * Реализует у класса механизм, который позволяет объекту этого класса получать оповещения об изменении состояния других объектов и тем самым наблюдать за ним
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Наблюдатель можно часто встретить в Java коде, особенно там, где применяется событийная модель отношений между компонентами.
 * Наблюдатель позволяет отдельным компонентам реагировать на события, происходящие в других компонентах.
 * <p>
 * <font color="#fa8e47">Примеры в стандартных библиотеках Java:<br></font>
 * Все реализации java.util.EventListener (практически во всём Swing-е)<br>
 * javax.servlet.http.HttpSessionBindingListener
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Наблюдатель можно определить по механизму подписки и методам оповещения, которые вызывают компоненты программы.
 * <p>
 * <font color="#fa8e47">Использование паттерна позволяет:<br></font>
 * <ul>
 * <li>существует как минимум один объект, рассылающий сообщения;</li>
 * <li>имеется не менее одного получателя сообщений, причём их количество и состав могут изменяться во время работы приложения;</li>
 * <li>позволяет избежать сильного зацепления взаимодействующих классов.</li></ul>
 *
 * <font color="#fa8e47">При реализации шаблона «наблюдатель» обычно используются следующие классы:<br></font>
 * <font color="#1E90FF">Observable</font> — интерфейс, определяющий методы для добавления, удаления и оповещения наблюдателей;<br>
 * <font color="#1E90FF">Observer</font> — интерфейс, с помощью которого наблюдатель получает оповещение;<br>
 * <font color="#1E90FF">ConcreteObservable</font> — конкретный класс, который реализует интерфейс Observable;<br>
 * <font color="#1E90FF">ConcreteObserver</font> — конкретный класс, который реализует интерфейс Observer.
 */


/*
пример - Учение Ктулху и его сектанты
При появлении каких-либо новых догматов (обязательных вероучений) и не только - эти люди должны знать о них.

1. У нас есть «голос культа», т. е. некий вещатель или субъект, оглашающий новости.
2. Есть сектанты, т. е. некие наблюдатели, которые хотят быть в курсе важных событий.
 */

// 1)Observable - некий культ, который будет вещать своей пастве о чем-либо, в котором можно зарегистрироваться или наоборот выйти из него
interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}

// 2) ConcreteObservable
class CultOfCthulhu implements Observable {
    private List<Observer> cultists;
    private String news;


    public CultOfCthulhu() {
        cultists = new ArrayList<>();
    }

    // событие изменения состояния наблюдаемого объекта
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        cultists.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        cultists.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer cultist : cultists)
            cultist.hearNews(news);
    }
}

// 3) Observer
interface Observer {
    void hearNews(String news);
}


// 4) ConcreteObserver
class Cultist implements Observer {
    private String name;

    public Cultist(String name) {
        this.name = name;
    }

    public void hearNews(String news) {
        System.out.println(name + " узнал новость: " + news);
    }
}


class Main {
    public static void main(String[] args) {
        CultOfCthulhu myCult = new CultOfCthulhu();

        Cultist c1 = new Cultist("Мартин Лютер");
        Cultist c2 = new Cultist("Жан Пьер");
        Cultist c3 = new Cultist("Доминик Дефо");

        myCult.registerObserver(c1);
        myCult.registerObserver(c2);
        myCult.registerObserver(c3);

        myCult.setNews("КТУЛХУ ФТАГН!");
        myCult.removeObserver(c3);
        System.out.println("------------");
        myCult.setNews("Ктулху зохавает фсех!");
    }
}
