package com.company.patterns.structural.facade;


/**
 * <h1>Фасад (facade)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * структурный паттерн, который предоставляет простой (но урезанный) интерфейс к сложной системе объектов, библиотеке или фреймворку.
 * <p>
 * Кроме того, что Фасад позволяет снизить общую сложность программы, он также помогает вынести код, зависимый от внешней системы в единственное место.
 * <p>
 * <font color="#fa8e47">Примеры Фасадов в стандартных библиотеках Java:<br></font>
 * javax.faces.context.FacesContext использует «под капотом» классы LifeCycle, ViewHandler, NavigationHandler
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>Фасад угадывается в классе, который имеет простой интерфейс,
 * но делегирует основную часть работы другим классам. Чаще всего, фасады сами следят за жизненным циклом объектов сложной системы.
 * <p>
 * Это одна из полезных особенностей данного паттерна – мы вольны работать с классами, которые скрыты интерфейсом (фасадом), напрямую, если в этом есть необходимость.
 * Если вам необходимо упростить работу с каким-либо интерфейсом или изолировать клиента от сложной системы, то паттерн Фасад будет идеальным выбором.
 */


/* Пример: навигационная система автомобиля
Перед поездкой водитель будет включать GPS, загружать данные о пробках, прокладывать путь и выключать после поездки.
За каждое описанное действие будет отвечать отдельный класс, кроме включения и выключения. */


// 1- класс для управления питанием
class GPSPower {
    public void powerOn() {
        System.out.println("Power ON");
    }

    public void powerOff() {
        System.out.println("Power OFF");
    }
}

// 2- класс  за получение информации о пробках на дорогах
class GPSNotifier {
    public void downloadRoadInfo() {
        System.out.println("Downloading road information...");
        System.out.println("Download complete!");
    }
}


// 3- класс чтобы проложить оптимальный маршрут
class RoadAdvisor {
    public void route() {
        System.out.println("Create a route");
    }
}

// в качестве фасада, будет выступать класс GPSInterface, который будет за водителя выполнять однотипные действия:
class GPSInterface {
    private GPSPower power;
    private GPSNotifier notifier;
    private RoadAdvisor advisor;

    public GPSInterface(GPSPower power, GPSNotifier notifier, RoadAdvisor advisor) {
        this.power = power;
        this.notifier = notifier;
        this.advisor = advisor;
    }

    public void activate() {
        power.powerOn();
        notifier.downloadRoadInfo();
        advisor.route();
    }

    public void powerOff() {
        power.powerOff();
    }
}


class Main {
    public static void main(String[] args) {
        GPSInterface gps = new GPSInterface(new GPSPower(), new GPSNotifier(), new RoadAdvisor());
        gps.activate();  //Водитель включает навигационную систему
        gps.powerOff();//Водитель выключает навигационную систему

        // без фасада работало бы так:
//        GPSPower power = new GPSPower();
//        GPSNotifier notifier = new GPSNotifier();
//        RoadAdvisor advisor = new RoadAdvisor();
//        power.powerOn();//Водитель включает навигационную систему
//        notifier.downloadRoadInfo();//Водитель нажимает кнопку загрузки информации о дорогах
//        advisor.route();   //Водитель нажимает кнопку прокладки маршрута
//        power.powerOff(); //Водитель выключает навигационную систему
    }
}
