package com.taxchina.autotest.crmnew.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log<T> {

    private static Logger Log = LogManager.getLogger(Log.class.getName());

    public static void startTestCase(String sTestCaseName) {
        Log.info("----------   " + sTestCaseName + "  测试用例执行开始"+"    ----------------");
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("----------   " + sTestCaseName + "  测试用例执行结束" + "    ----------------");
    }

    public static<T> void info(T message) {
        Log.info(message);
    }

    public static<T> void warn(T  message) {
        Log.warn(message);
    }

    public static<T> void error(T  message) {
        Log.error(message);
    }

    public static<T> void fatal(T  message) {
        Log.fatal(message);
    }

    public static<T> void debug(T  message) {
        Log.debug(message);
    }
}