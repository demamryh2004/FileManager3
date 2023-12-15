import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Класс для обработки операций с файлами.
 */
public class FileOperationHandler {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    /**
     * Копирует файл из одного места в другое.
     * @param scanner Объект Scanner для ввода пользователя.
     */
    public static void copyFile(Scanner scanner) {
        System.out.print("Введите полный путь к исходному файлу (например, C:\\путь\\к\\файлу.txt): ");
        String sourceFilePath = scanner.nextLine();

        System.out.print("Введите полный путь к целевой директории (например, C:\\путь\\к\\директория): ");
        String targetDirectoryPath = scanner.nextLine();

        try {
            File sourceFile = new File(sourceFilePath);

            if (!sourceFile.exists() || !sourceFile.isFile()) {
                logger.error("Ошибка: Исходный файл не существует.");
                return;
            }

            if (!isValidDirectory(targetDirectoryPath)) {
                return;
            }

            String targetFilePath = targetDirectoryPath + File.separator + sourceFile.getName();
            copyFileContents(sourceFile, targetFilePath);

        } catch (FileNotFoundException e) {
            logger.error("Ошибка: Недостаточно прав для записи в целевую директорию.");
        } catch (IOException e) {
            logger.error("Ошибка: Не удалось записать файл.");
        }
    }

    /**
     * Копирует содержимое файла в другую директорию.
     * @param sourceFile      Исходный файл.
     * @param targetFilePath  Путь к целевому файлу.
     * @throws IOException    Если произошла ошибка ввода/вывода.
     */
    private static void copyFileContents(File sourceFile, String targetFilePath) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(targetFilePath)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            logger.info("Файл успешно скопирован в " + targetFilePath);

        }
    }

    /**
     * Выводит размер файлов в указанной директории.
     * @param scanner Объект Scanner для ввода пользователя.
     */
    public static void displayFileSize(Scanner scanner) {
        System.out.print("Введите полный путь к директории (например, C:\\путь\\к\\директория): ");
        String directoryPath = scanner.nextLine();

        if (!isValidDirectory(directoryPath)) {
            return;
        }

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            logger.error("Ошибка: В директории " + directoryPath + " нет файлов.");
            return;
        }

        logger.info("Вывод размеров файлов в директории " + directoryPath + ":");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName() + ": " + file.length() + " байт");
            }
        }
    }

    /**
     * Проверяет, является ли указанный путь допустимой директорией.
     * @param directoryPath Путь к директории.
     * @return true, если директория допустима, в противном случае false.
     */
    private static boolean isValidDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            logger.error("Ошибка: Указанной директории не существует.");
            return false;
        }
        return true;
    }
}
