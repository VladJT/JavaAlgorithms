package com.company.patterns.structural.proxy;

import java.io.IOException;

/**
 * <h1>Заместитель (PROXY)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐</font>⭐⭐<p></h2>
 * объект, который выступает прослойкой между клиентом и реальным сервисным объектом.
 * Заместитель получает вызовы от клиента, выполняет свою функцию (контроль доступа, кеширование, изменение запроса и прочее),
 * а затем передаёт вызов сервисному объекту.
 * <p>
 * PROXY реализует интерфейс сервисного класса, имитируя его поведение для клиентского кода.
 * Таким образом вместо реального объекта клиент взаимодействует с его заместителем.
 * Как правило, все запросы передаются далее сервисному классу, но с дополнительными действиями до или после его вызова.
 * Прокси-объект — прослойка между клиентским кодом и целевым объектом.
 * <p>
 * <font color="#fa8e47">Варианты использования:<br></font>
 * 1. Кэширование.<br>
 * 2. Отложенная реализация, также известная как ленивая. Зачем загружать объект сразу, если можно загрузить его по мере необходимости?<br>
 * 3. Логирование запросов.<br>
 * 4. Промежуточные проверки данных и доступа.<br>
 * 5. Запуск параллельных потоков обработки.<br>
 * 6. Запись или подсчет истории обращения.
 * <p>
 * На первый взгляд, Заместитель делает то же, что и Фасад, но это не так. У Заместителя есть тот же интерфейс, что и у сервисного объекта.
 * Также не нужно путать паттерн с Декоратором или Адаптером. Декоратор предоставляет расширенный интерфейс, а Адаптер — альтернативный.
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:</font>
 * Класс заместителя чаще всего делегирует всю настоящую работу своему реальному объекту. Заместители часто сами следят за жизненным циклом своего реального объекта.
 * <p>
 * <font color="#fa8e47">Примеры Заместителя в стандартных библиотеках Java:</font>
 * <li>java.lang.reflect.Proxy</>
 * <li>java.rmi.*</>
 * <li>javax.persistence.PersistenceContext</>
 */


interface ICommandExecutor {
    public void runCommand(String cmd) throws Exception;
}


// сервисный объект для запуска команд консоли
class SystemCommandExecutor implements ICommandExecutor {
    @Override
    public void runCommand(String cmd) throws IOException {
        //Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' команда выполнена");
    }
}

// Proxy (мы хотим предоставить полный доступ только пользователям с правами администратора вышеуказанного класса)
class CommandExecutorProxy implements ICommandExecutor {
    private boolean isAdmin;
    private ICommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd) {
        if ("vlad".equals(user) && "Qwerty12".equals(pwd)) isAdmin = true;
        executor = new SystemCommandExecutor();
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if (isAdmin) {
            executor.runCommand(cmd);
        } else {
            if (cmd.trim().startsWith("rm")) {
                throw new Exception("rm command запрещена для запуска пользователям");
            } else {
                executor.runCommand(cmd);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        ICommandExecutor executor = new CommandExecutorProxy("vlad", "wrong_pwd");
        try {
            executor.runCommand("ls -ltr"); // доступна пользователям
            executor.runCommand(" rm -rf abc.pdf"); // только для админа
        } catch (Exception e) {
            System.out.println("Exception::" + e.getMessage());
        }
    }
}
