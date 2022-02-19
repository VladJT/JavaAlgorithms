package com.company.patterns.behavioral.observer;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


// ConcreteObservable
/*
Центральную роль в процессе генерации событий занимает класс PropertyChangeSupport.
Он позволяет генерировать поток событий при помощи функции firePropertyChange().
Куда же отправляются генерируемые события?
Они отправляются подписчикам.
Подписка осуществляется при помощи функции addPropertyChangeListener(),
которая принимает в качестве параметров указатель на объект реализации интерфейса PropertyChangeListener.
В процессе подписки мы можем либо получать все события интересующего нас объекта,
либо выбрать конкретное событие, добавляя подписку на каждое из них по очереди.
 */
class CultOfCthulhu2 {
    protected String news;
    // используя support мы можем добавлять или удалять наших прихожан (слушателей)
    private PropertyChangeSupport support;


    public CultOfCthulhu2() {
        support = new PropertyChangeSupport(this);
    }

    public void registerObserver(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removeObserver(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setNews(String value) {
        //В качестве параметров к ней может передаваться либо готовый объект событий класса PropertyChangeEvent,
        //либо три параметра: название события, старое значение, новое значение.
        support.firePropertyChange("событие - рассылка новостей", this.news, value);
        this.news = value;
    }

    public void callOfCthulhu() {
        //В качестве параметров к ней может передаваться либо готовый объект событий класса PropertyChangeEvent,
        //либо три параметра: название события, старое значение, новое значение.
        support.firePropertyChange("событие - зов Ктулху", "1", "0");
    }
}


//  ConcreteObserver
class Cultist2 implements PropertyChangeListener {
    private String name;

    public Cultist2(String name) {
        this.name = name;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "событие - рассылка новостей")
            System.out.println(name + " узнал новость: " + evt.getNewValue());
        if (evt.getPropertyName() == "событие - зов Ктулху") {
            System.out.println(name + " готов служить ");
        }
    }
}

class PropertyChangeSupportExample {
    public static void main(String[] args) {
        CultOfCthulhu2 myCult = new CultOfCthulhu2();

        Cultist2 c1 = new Cultist2("Иван");
        Cultist2 c2 = new Cultist2("Петя");
        Cultist2 c3 = new Cultist2("Слава");

        myCult.registerObserver(c1);
        myCult.registerObserver(c2);
        myCult.registerObserver(c3);

        myCult.setNews("КТУЛХУ ФТАГН!");
        System.out.println("------------");
        myCult.callOfCthulhu();
    }
}
