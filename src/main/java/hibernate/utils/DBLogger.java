package hibernate.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Sergiy.K on 27-Jan-17.
 */
public class DBLogger {
    public static final Logger LOGGER = Logger.getLogger(DBLogger.class);
    private static final String INFO_LOG = "INFO: %s ";
    private static final String ERROR_LOG = "ERROR: %s ";


    public static String info(String message) {
        BasicConfigurator.configure();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF); //or whatever level you need
        LOGGER.getRootLogger().setLevel(Level.OFF);
        LOGGER.setLevel(Level.INFO);
        LOGGER.info(String.format(INFO_LOG , message));
        return String.format(INFO_LOG,message);
//        return "";
    }

    public static String error(String message) {
        LOGGER.setLevel(Level.ERROR);
        BasicConfigurator.configure();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF); //or whatever level you need
        LOGGER.getRootLogger().setLevel(Level.OFF);
        LOGGER.error(String.format(ERROR_LOG, message));
        return String.format(ERROR_LOG, message);
    }
}
