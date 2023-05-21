import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleToFile {
    public static void main(String[] args) {
        ConsoleToFile consoleToFile = new ConsoleToFile();
        consoleToFile.start();
    }

    private void start() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String filePath = getFilePathFromConsole(consoleReader);
        File file = new File(filePath);

        while (!file.exists()) {
            System.out.println("Файл не існує. Введіть інший шлях до файлу:");
            filePath = getFilePathFromConsole(consoleReader);
            file = new File(filePath);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String input;
            System.out.println("Введіть текст для запису у файл (Ctrl + D для завершення):");
            while ((input = consoleReader.readLine()) != null && !input.equals("\u001a")) {
                writer.write(input);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Сталася помилка під час запису в файл: " + e.getMessage());
        }

        System.out.println("Запис завершено.");
    }

    private String getFilePathFromConsole(BufferedReader reader) {
        System.out.println("Введіть шлях до файлу:");
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Сталася помилка при читанні шляху: " + e.getMessage());
            return null;
        }
    }
}
