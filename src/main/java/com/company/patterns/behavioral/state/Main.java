package com.company.patterns.behavioral.state;


/**
 * <h1>Состояние (State)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐</font>⭐⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * поведенческий паттерн, позволяющий динамически изменять поведение объекта при смене его состояния
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн Состояние часто используют в Java для превращения в объекты громоздких стейт-машин, построенных на операторах switch.
 * <p>
 * <font color="#fa8e47">Примеры в стандартных библиотеках Java:<br></font>
 * javax.faces.lifecycle.LifeCycle#execute() (контролируемый из FacesServlet: поведение зависит от текущей фазы (состояния) JSF)
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Методы класса делегируют работу одному вложенному объекту
 * <p>
 * <font color="#fa8e47"> Паттерн состоит из 3 блоков:<br></font>
 * <ul>
 * <li>Widget — класс, объекты которого должны менять своё поведение в зависимости от состояния.</li>
 * <li> IState — интерфейс, который должен реализовать каждое из конкретных состояний. Через этот интерфейс объект Widget взаимодействует с состоянием, делегируя ему вызовы методов.
 *  Интерфейс должен содержать средства для обратной связи с объектом, поведение которого нужно изменить. Для этого используется событие (паттерн Publisher — Subscriber).
 *  Это необходимо для того, чтобы в процессе выполнения программы заменять объект состояния при появлении событий.
 *  Возможны случаи, когда сам Widget периодически опрашивает объект состояния на наличие перехода</li>
 * <li>StateA … StateZ — классы конкретных состояний. Должны содержать информацию о том, при каких условиях и в какие состояния может переходить объект из текущего состояния.
 *  Например, из StateA объект может переходить в состояние StateB и StateC, а из StateB — обратно в StateA и так далее. Объект одного из них должен содержать Widget при создании.</li>
 * </ul>
 *  <font color="#fa8e47">Используйте паттерн состояние в следующих случаях:<br></font>
 * <ul>
 *  <li> Когда поведение объекта зависит от его состояния и при этом должно изменяться во время выполнения..</li>
 * <li>Когда в коде операций встречаются состоящие из многих ветвей условные операторы, в которых выбор ветви зависит от состояния. Обычно в таком случае состояние представлено перечисляемыми константами. Часто одна и та же структура условного оператора повторяется в нескольких операциях.
 * Паттерн состояние предлагает поместить каждую ветвь в отдельный класс. Это позволяет трактовать состояние объекта как самостоятельный объект, который может изменяться независимо от других.</li></ul>
 *  <font color="ffcc00">Важно! Используйте паттерн Стратегия для инкапсулирования алгоритма или стратегии, который предоставляется контексту во время выполнения,
 *  возможно как параметр или составной объект и используйте паттерн Состояние для управления переходами между состояниями в Java.</font>
 */

// общий интерфейс для состояний воды
interface State {
    String getName();

    void freeze(StateContext context);

    void heat(StateContext context);
}

// ТВЕРДОЕ СОСТОЯНИЕ
class SolidState implements State {
    private static final String NAME = "ЛЁД";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        System.out.println("Состояние не меняется");
    }

    public void heat(StateContext context) {
        context.setState(new LiquidState());
    }
}

// ЖИДКОЕ СОСТОЯНИЕ
class LiquidState implements State {
    private static final String NAME = "ВОДА";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        context.setState(new SolidState());
    }

    public void heat(StateContext context) {
        context.setState(new GaseousState());
    }
}

// ГАЗООБРАЗНОЕ СОСТОЯНИЕ
class GaseousState implements State {
    private static final String NAME = "ПАР";

    public String getName() {
        return NAME;
    }

    public void freeze(StateContext context) {
        context.setState(new LiquidState());
    }

    public void heat(StateContext context) {
        System.out.println("Состояние не меняется");
    }
}

class StateContext {
    private State state = new SolidState();

    public void freeze() {
        System.out.println("Замораживаем " + state.getName());
        state.freeze(this);
    }

    public void heat() {
        System.out.println("Нагреваем " + state.getName());
        state.heat(this);
    }

    public void setState(State state) {
        System.out.println("Состояние изменено на " + state.getName() + " \n....");
        this.state = state;
    }

    public State getState() {
        return state;
    }

}

class Main {
    public static void main(String[] args) {
        StateContext context = new StateContext();
        context.heat();
        context.heat();
        context.heat();
        context.freeze();
        context.freeze();
        context.freeze();
    }
}
