package fileexplorer;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    /**
     * Вывод списка файлов и папок для заданной директории с информацией о размере и дате изменения.
     *
     * @param pathStr Путь к директории.
     */
    public static void listDirectory(String pathStr) {
        File dir = new File(pathStr);

        if (!dir.exists()) {
            System.out.println("Указанный путь не существует.");
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("Указанный путь не является директорией.");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Директория пуста.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Содержимое директории " + pathStr + ":");
        for (File file : files) {
            String name = file.getName();
            String type = file.isDirectory() ? "DIR" : "FILE";
            long size = file.length();
            String lastModified = sdf.format(new Date(file.lastModified()));
            System.out.printf("%s - %s, Размер: %d байт, Изменен: %s%n", name, type, size, lastModified);
        }
    }

    /**
     * Создает новый файл по указанному пути.
     *
     * @param pathStr Путь и имя файла.
     */
    public static void createFile(String pathStr) {
        File file = new File(pathStr);
        try {
            if (file.exists()) {
                System.out.println("Файл уже существует.");
            } else {
                if (file.createNewFile()) {
                    System.out.println("Файл успешно создан: " + file.getAbsolutePath());
                } else {
                    System.out.println("Не удалось создать файл.");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    /**
     * Записывает данные в файл. Если append=true, данные будут дозаписаны в конец файла,
     * иначе содержимое файла будет перезаписано.
     *
     * @param pathStr Путь к файлу.
     * @param data    Текстовые данные для записи.
     * @param append  Флаг дозаписи.
     */
    public static void writeFile(String pathStr, String data, boolean append) {
        File file = new File(pathStr);
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.write(data);
            writer.flush();
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Читает содержимое файла и выводит его в консоль.
     *
     * @param pathStr Путь к файлу.
     */
    public static void readFile(String pathStr) {
        File file = new File(pathStr);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Файл не существует или не является файлом.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Содержимое файла:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    /**
     * Переименовывает файл. Новый файл создается в той же директории, но с другим именем.
     *
     * @param pathStr Путь к существующему файлу.
     * @param newName Новое имя файла (без пути).
     */
    public static void renameFile(String pathStr, String newName) {
        File file = new File(pathStr);
        if (!file.exists()) {
            System.out.println("Файл не существует.");
            return;
        }

        File newFile = new File(file.getParent(), newName);
        if (newFile.exists()) {
            System.out.println("Файл с новым именем уже существует.");
            return;
        }

        boolean success = file.renameTo(newFile);
        if (success) {
            System.out.println("Файл успешно переименован в: " + newFile.getAbsolutePath());
        } else {
            System.out.println("Не удалось переименовать файл.");
        }
    }

    /**
     * Копирует файл из исходного пути в целевой путь.
     * Используется метод Files.copy() из пакета java.nio.file.
     *
     * @param sourcePathStr Путь к исходному файлу.
     * @param destPathStr   Путь к новому файлу (включая имя).
     */
    public static void copyFile(String sourcePathStr, String destPathStr) {
        Path sourcePath = Paths.get(sourcePathStr);
        Path destPath = Paths.get(destPathStr);

        if (!Files.exists(sourcePath)) {
            System.out.println("Исходный файл не существует.");
            return;
        }

        try {
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл успешно скопирован в: " + destPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }

    /**
     * Удаляет файл по указанному пути.
     *
     * @param pathStr Путь к файлу.
     */
    public static void deleteFile(String pathStr) {
        File file = new File(pathStr);
        if (!file.exists()) {
            System.out.println("Файл не существует.");
            return;
        }

        if (file.delete()) {
            System.out.println("Файл успешно удалён.");
        } else {
            System.out.println("Не удалось удалить файл.");
        }
    }
}

