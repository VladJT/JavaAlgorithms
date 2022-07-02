package com.company.core.logger;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


class Log4jEx {

    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/logs/configs/log4j.properties");
        Logger admin = Logger.getLogger("admin");
        admin.info("info");
        admin.warn("warn");

    }
}
