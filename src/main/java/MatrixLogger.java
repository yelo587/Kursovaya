import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для логирования с помощью log4j.
 */
public class MatrixLogger {
    private static final Logger logger = LogManager.getLogger(MatrixLogger.class);

    /**
     * Логирование информации.
     *
     * @param message сообщение для логирования
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Логирование ошибки.
     *
     * @param message сообщение об ошибке
     */
    public static void error(String message) {
        logger.error(message);
    }
}
