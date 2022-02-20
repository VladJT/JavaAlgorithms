package com.company.patterns.behavioral.command;


import java.util.HashMap;

/**
 * The Command interface
 */
interface ICommand {
    void execute();
}

/**
 * The Invoker class
 */
class Switch {
    private final HashMap<String, ICommand> commandMap = new HashMap<>();

    public void register(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName) {
        ICommand command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("не зарегистрирована команда: " + commandName);
        }
        command.execute();
    }
}

/**
 * The Receiver class
 */
class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}


/**
 * The Command for turning on the light - ConcreteCommand #1
 */
class SwitchOnCommand implements ICommand {
    private final Light light;

    public SwitchOnCommand(Light light) {
        this.light = light;
    }

    @Override // Command
    public void execute() {
        light.turnOn();
    }
}

/**
 * The Command for turning off the light - ConcreteCommand #2
 */
class SwitchOffCommand implements ICommand {
    private final Light light;

    public SwitchOffCommand(Light light) {
        this.light = light;
    }

    @Override // Command
    public void execute() {
        light.turnOff();
    }
}


class FuncionalInterfaceEx {
    public static void main(String[] args) {
        Light lamp = new Light();//Receiver

        // не требуется обязательно создавать классы SwitchOnCommand и SwitchOffCommand вместо этого мы можем использовать оператор :: как показано в следующем примере
        // ICommand switchOn = new SwitchOnCommand(lamp);
        // ICommand switchOff = new SwitchOffCommand(lamp);
        ICommand switchOn = lamp::turnOn;
        ICommand switchOff = lamp::turnOff;

        Switch mySwitch = new Switch();//Invoker
        mySwitch.register("on", switchOn);
        mySwitch.register("off", switchOff);

        mySwitch.execute("on");
        mySwitch.execute("off");
    }
}
