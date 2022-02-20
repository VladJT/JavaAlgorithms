package com.company.patterns.behavioral.command;

/**
 * <h1>Команда (Command)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * поведенческий шаблон проектирования, используемый при объектно-ориентированном программировании, представляющий действие.
 * Объект команды заключает в себе само действие и его параметры
 * <p>
 * <font color="#fa8e47">Применимость:<br></font>
 * Паттерн можно часто встретить в Java-коде, особенно когда нужно откладывать выполнение команд, выстраивать их в очереди, а также хранить историю и делать отмену.
 * <p>
 * <font color="#fa8e47">Примеры Шаблонных методов в стандартных библиотеках Java:<br></font>
 * Все реализации java.lang.Runnable<br>
 * Все реализации javax.swing.Action
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>
 * Классы команд построены вокруг одного действия и имеют очень узкий контекст.
 * Объекты команд часто подаются в обработчики событий элементов GUI. Практически любая реализация отмены использует принцип команд.
 * <p>
 * <font color="#fa8e47">Шаблон Команда может быть полезен в следующих случаях:<br></font>
 * <ul>
 * <li>Кнопки пользовательского интерфейса и пункты меню</li>
 * <li>Запись макросовю</li>
 * <li>Многоуровневая отмена операций (Undo)</li>
 * <li>Сети</li>
 * <li>Индикаторы выполнения</li>
 * <li>Пулы потоков</li>
 * <li>Транзакции</li>
 * </ul>
 * В этом паттерне есть четыре термина, пока примем их как данность:<br>
 * команды(command), приемник команд(receiver), вызывающий команды(invoker) и клиент(client).
 */

/*
Мы рассмотрим реальный жизненный сценарий, в котором мы можем реализовать командный шаблон.
Допустим, мы хотим предоставить утилите файловой системы методы для открытия, записи и закрытия файла.
Эта утилита файловой системы должна поддерживать несколько операционных систем, таких как Windows и Unix.
 */


// 1) приемник команд(receiver)
interface FileSystemReceiver {
    void openFile();

    void writeFile();

    void closeFile();
}

class UnixFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
        System.out.println("Opening file in unix OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Writing file in unix OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing file in unix OS");
    }
}

class WindowsFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
        System.out.println("Opening file in Windows OS");

    }

    @Override
    public void writeFile() {
        System.out.println("Writing file in Windows OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing file in Windows OS");
    }
}

// 2) команды(command)
interface Command {
    void execute();
}

class OpenFileCommand implements Command {
    private FileSystemReceiver fileSystem;

    public OpenFileCommand(FileSystemReceiver fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute() {
        //open command is forwarding request to openFile method
        this.fileSystem.openFile();
    }
}

class CloseFileCommand implements Command {

    private FileSystemReceiver fileSystem;

    public CloseFileCommand(FileSystemReceiver fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute() {
        this.fileSystem.closeFile();
    }

}

class WriteFileCommand implements Command {

    private FileSystemReceiver fileSystem;

    public WriteFileCommand(FileSystemReceiver fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }

}

// 3) вызывающий команды(invoker)
class FileInvoker {
    public Command command;

    public FileInvoker(Command c) {
        this.command = c;
    }

    public void execute() {
        this.command.execute();
    }
}

class FileSystemReceiverUtil {

    public static FileSystemReceiver getUnderlyingFileSystem() {
        String osName = System.getProperty("os.name");
        System.out.println("Underlying OS is: " + osName);
        if (osName.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new UnixFileSystemReceiver();
        }
    }
}

class Main {
    public static void main(String[] args) {
        //Creating the receiver object
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        //creating command and associating with receiver
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        //Creating invoker and associating with Command
        FileInvoker file = new FileInvoker(openFileCommand);

        //perform action on invoker object
        file.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        file = new FileInvoker(writeFileCommand);
        file.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        file = new FileInvoker(closeFileCommand);
        file.execute();
    }
}
