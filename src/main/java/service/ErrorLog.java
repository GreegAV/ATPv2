package service;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorLog {
    private static Logger logger = Logger.getLogger(ErrorLog.class);

    public static void logError(String msg, Exception e) {
        logger.error(msg);
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        logger.error(errors.toString());
    }

    public static void logInfo(String msg) {
        logger.info(msg);
    }

}
