import fileexplorer.FileManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Простое файловое приложение ===");
            System.out.println("1. Вывести список файлов и папок");
            System.out.println("2. Создать файл");
            System.out.println("3. Записать данные в файл");
            System.out.println("4. Прочитать данные из файла");
            System.out.println("5. Переименовать файл");
            System.out.println("6. Копировать файл");
            System.out.println("7. Удалить файл");
            System.out.println("8. Выход");
            System.out.print("Выберите операцию: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите путь директории: ");
                    String dirPath = scanner.nextLine();
                    FileManager.listDirectory(dirPath);
                    break;
                case 2:
                    System.out.print("Введите путь и имя нового файла: ");
                    String newFilePath = scanner.nextLine();
                    FileManager.createFile(newFilePath);
                    break;
                case 3:
                    System.out.print("Введите путь файла для записи: ");
                    String writeFilePath = scanner.nextLine();
                    System.out.print("Введите текст для записи: ");
                    String data = scanner.nextLine();
                    System.out.print("Добавить (append) или перезаписать файл? (a/p): ");
                    String mode = scanner.nextLine();
                    boolean append = mode.equalsIgnoreCase("a");
                    FileManager.writeFile(writeFilePath, data, append);
                    break;
                case 4:
                    System.out.print("Введите путь файла для чтения: ");
                    String readFilePath = scanner.nextLine();
                    FileManager.readFile(readFilePath);
                    break;
                case 5:
                    System.out.print("Введите путь файла для переименования: ");
                    String oldFilePath = scanner.nextLine();
                    System.out.print("Введите новое имя (без пути): ");
                    String newName = scanner.nextLine();
                    FileManager.renameFile(oldFilePath, newName);
                    break;
                case 6:
                    System.out.print("Введите путь исходного файла: ");
                    String sourcePath = scanner.nextLine();
                    System.out.print("Введите путь для копирования файла (с именем): ");
                    String destPath = scanner.nextLine();
                    FileManager.copyFile(sourcePath, destPath);
                    break;
                case 7:
                    System.out.print("Введите путь файла для удаления: ");
                    String deletePath = scanner.nextLine();
                    FileManager.deleteFile(deletePath);
                    break;
                case 8:
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }

            System.out.println();
        }
    }
}
