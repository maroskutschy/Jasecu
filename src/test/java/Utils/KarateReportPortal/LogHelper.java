package Utils.KarateReportPortal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a Logger class to log events at various log levels
 *
 */
public class LogHelper {
    /**
     * The constant className.
     */
    public static String className;

    /**
     * This method is to create Logger object which will determine method caller and get class name
     *
     * @return Logger object
     */
    public static Logger getLogger() {
        final Throwable t = new Throwable();
        final StackTraceElement methodCaller = t.getStackTrace()[1];
        return LoggerFactory.getLogger((className == null) ? methodCaller.getClassName().getClass() : className.getClass());
    }
}
