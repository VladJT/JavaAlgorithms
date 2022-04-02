package com.company.core.logger;

// Appender
//Appender осуществляет запись сообщения в “пункт назначения”: консоль, файл, базу данных, и т.д.
//Appender это интерфейс Log4j2, а его реализациями являются: ConsoleAppender, FileAppender,
//JDBCAppender, SocketAppender, SyslogAppender, и еще очень много других классов.

//Layout
//Layout отвечает за форматирование выводимых сообщений, и в Log4j представлены: PatternLayout,
//HtmlLayout, YAMLLayout, XmlLayout, JSONLayout.


import java.io.IOException;
import java.util.Date;
import java.util.logging.*;


class UtilsLoggingEx {
    static Logger logger = Logger.getLogger("testlog");
    //Уровни
    // OFF
    // SEVERE
    // WARNING
    // ------
    // CONFIG
    // FINE (3)
    // ALL

    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.ALL);

        Handler handler = new FileHandler("src/main/resources/logs/logFromLogger.log");
        logger.addHandler(handler);
        //handler.setFormatter(new XMLFormatter());
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("%s\t%s\t%s\n",record.getLevel(), new Date(record.getMillis()),record.getMessage());
            }
        });
        handler.setLevel(Level.ALL);

        logger.log(Level.SEVERE, "Логирование SEVERE");
        logger.finer("логирование finer");
        logger.info("info сообщение");
        logger.warning("Warning сообщение");
        logger.config("Config сообщение");
    }

}
