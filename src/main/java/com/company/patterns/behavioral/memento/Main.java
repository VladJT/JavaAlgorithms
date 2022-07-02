package com.company.patterns.behavioral.memento;


import java.util.ArrayList;

/**
 * <h1>Хранитель  (Memento) </h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * поведенческий шаблон проектирования, позволяющий, не нарушая инкапсуляцию, зафиксировать и сохранить внутреннее состояние объекта так, чтобы позднее восстановить его в это состояние.<br>
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 *  <ul><li>необходимо сохранить снимок состояния объекта (или его части) для последующего восстановления</li>
 * <li>прямой интерфейс получения состояния объекта раскрывает детали реализации и нарушает инкапсуляцию объекта</li></ul>
 * <p>
 * <font color="#fa8e47"> Участники процесса:<br></font>
 * <b>Memento («хранитель»)</b> – хранитель, сохраняет состояние объекта Originator;<br>
 * <b>Originator («создатель»)</b> – создает экземпляр объекта хранителя. Имеет полный доступ к Memento;<br>
 * <b>Caretaker («опекун»)</b> – производит сохранения состояний.
 * <p>
 * <font color="ffcc00">Важно! </font>
 */

/*
Давайте разберем этот паттерн на практике. Для примера мне вспомнилась игра Resident Evil (части 1, 2, 3) для консоли Play Station 1.
Мне там очень понравилось, как реализована система здоровья. В игре не показывался процент здоровья, а только индикатор пульса.
Если индикатор зеленый — то все хорошо, если желтый – среднее состояние, если красный – очень опасно для жизни.
И в зависимости от этого главный герой либо идет ровно, либо придерживается рукой за живот и медленней передвигается, либо еле плетется. П
редставим, что мы разработчики этой игры и реализуем систему быстрых сохранений и загрузок состояния здоровья персонажа.
И так у нас будет 3 класса: Player(класс игрока, выступает в роли ”Создателя”),
GameTools(класс в котором будет реализовано быстрое сохранение и загрузка состояния игрока, выступает в роли ”Опекуна”)
и класс Memento(который будет выступать в роли ”Хранителя”). Так как процент состояния здоровья скрыт от нас, то он и будет исполнять роль внутреннего состояния объекта Player.
 */

// интерфейс, реализующий логику объекта Originator
interface IOriginator {
    Memento GetMemento();

    void SetMemento(Memento memento);
}

class Player implements IOriginator {
    private int health;

    public Player() {
        this.health = 100;
    }

    public void GetHurt(int hurt) {
        System.out.println("Нанесен урон: " + hurt);
        health -= hurt;
    }

    public void GetCure(int cure) {
        System.out.println("Лечение: " + cure);
        health += cure;
    }

    public void PrintPulse() {
        System.out.println("health = " + health);
    }

    @Override
    public Memento GetMemento() {
        return new Memento(health);
    }

    @Override
    public void SetMemento(Memento memento) {
        health = memento.GetState();
    }
}

// Хранитель
class Memento {
    private int health;

    public Memento(int health) {
        this.health = health;
    }

    public int GetState() {
        return health;
    }
}

class GameUtils {
    private ArrayList<Memento> memento = new ArrayList<>();

    public void SaveState(IOriginator originator) {
        memento.add(originator.GetMemento());
        System.out.println("Save state");
    }

    public void LoadState(IOriginator originator, int saveNum) {
        originator.SetMemento(memento.get(saveNum));
        System.out.println("Load State");
    }
}

class Main {
    public static void main(String[] args) {
        GameUtils gameUtils = new GameUtils();
        Player player = new Player();

        player.GetHurt(20); //нанесено урон 20
        gameUtils.SaveState(player);  //сохраняем состояние 0
        player.GetHurt(30); //нанесено урон 30
        gameUtils.SaveState(player);  //сохраняем состояние 1
        player.GetHurt(20); //нанесено урон 20
        gameUtils.SaveState(player);  //сохраняем состояние 2
        player.PrintPulse();//печатаем пульс


        gameUtils.SaveState(player);  //сохраняем состояние 3

        player.GetCure(30); //принимаем лекарство
        player.PrintPulse();//печатаем пульс

        //восстанавливаем состояние 2
        gameUtils.LoadState(player, 1);

        player.PrintPulse(); //печатаем пульс

    }
}
