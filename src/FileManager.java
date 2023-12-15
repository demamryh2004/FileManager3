import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Главный класс для управления файлами.
 */
public class FileManager {

    /**
     * Точка входа в приложение.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        System.setProperty("logs/application.log", "C:\\Users\\Dimam\\IdeaProjects\\Kyrcash\\log4j2.xml");
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.reconfigure();

        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.startConsoleUI();
    }
}