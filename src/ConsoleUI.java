import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Класс для взаимодействия с пользователем через консоль.
 */
public class ConsoleUI {
    private static final Logger logger = LogManager.getLogger(ConsoleUI.class);

    /**
     * Запускает консольный интерфейс.
     */
    void startConsoleUI() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = getUserChoice(scanner);

            if (choice == 3) {
                break;
            }

            switch (choice) {
                case 1:
                    FileOperationHandler.copyFile(scanner);
                    break;
                case 2:
                    FileOperationHandler.displayFileSize(scanner);
                    break;
                default:
                    logger.error("Ошибка: Некорректный выбор.");
            }
        }

        scanner.close();
    }

    /**
     * Получает выбор пользователя.
     * @param scanner Объект Scanner для ввода пользователя.
     * @return Выбор пользователя.
     */
    private static int getUserChoice(Scanner scanner) {
        System.out.print("Что вы хотите сделать? (1 - Копировать файл, 2 - Вывести список файлов, 3 - Выйти): ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            scanner.nextLine();
            return -1;
        }
    }

}
