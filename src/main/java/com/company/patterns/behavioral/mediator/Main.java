package com.company.patterns.behavioral.mediator;


import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Посредник (Mediator)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * поведенческий шаблон проектирования, обеспечивающий взаимодействие множества объектов, формируя при этом слабое зацепление и избавляя объекты от необходимости явно ссылаться друг на друга<br>
 * Задача: Обеспечить взаимодействие множества объектов, сформировав при этом слабую связанность и избавив объекты от необходимости явно ссылаться друг на друга.
 * <p>
 * <font color="#fa8e47">Примеры использования Посредника в стандартных библиотеках Java:<br></font>
 * java.util.Timer (все методы scheduleXXX())<br>
 * java.util.concurrent.Executor#execute()
 * <p>
 * <font color="#fa8e47">Преимущества:<br></font>
 * <li>Устраняется связанность между "Коллегами"</li>
 * <li>централизуется управление</li>
 */

/*
В нашем примере мы попытаемся реализовать чат-приложение, в котором пользователи могут вести групповой чат.
Каждый пользователь будет идентифицирован по своему имени, и он сможет отправлять и получать сообщения.
Сообщение, отправленное любым пользователем, должно быть получено всеми остальными пользователями группы.
 */

// интерфейс Посредника (Медиатора)
interface ChatMediator {
    public void sendMessage(String msg, User user);

    void addUser(User user);
}


// интерфейс "Коллеги"
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}


//  конкретный класс посредника, который будет иметь список пользователей в группе и обеспечивать логику общения между пользователями
class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            //message should not be received by the user sending it
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}


class UserImpl extends User {

    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    // метод send() использует посредника для отправки сообщения пользователям и понятия не имеет, как оно будет обработано посредником.
    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message:" + msg);
    }

}

class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Pankaj");
        User user2 = new UserImpl(mediator, "Lisa");
        User user3 = new UserImpl(mediator, "Saurabh");
        User user4 = new UserImpl(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }

}
