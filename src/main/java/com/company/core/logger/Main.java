package com.company.core.logger;

// Appender
//Appender осуществляет запись сообщения в “пункт назначения”: консоль, файл, базу данных, и т.д.
//Appender это интерфейс Log4j2, а его реализациями являются: ConsoleAppender, FileAppender,
//JDBCAppender, SocketAppender, SyslogAppender, и еще очень много других классов.

//Layout
//Layout отвечает за форматирование выводимых сообщений, и в Log4j представлены: PatternLayout,
//HtmlLayout, YAMLLayout, XmlLayout, JSONLayout.


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        LOGGER.debug("Debug");
        LOGGER.info("Info");
        LOGGER.warn("Warn");
        LOGGER.error("Error");
      //  LOGGER.fatal("Fatal");
        LOGGER.info("String: {}.", "Hello, World");
    }

}
