import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Запросити від користувача назву файлу
        System.out.print("Введіть назву файлу: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        // Створити повний шлях до файлу
        String filePathString = "D:\\java proj\\Main\\src\\" + fileName;
        Path filePath = Paths.get(filePathString);

        // Перевірити, чи існує файл
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            System.out.println("Файл не існує або не є звичайним файлом.");
            return;
        }

        List<Integer> numbers = new ArrayList<>();
        int sum = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathString))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    numbers.add(number);
                    sum += number;
                    count++;
                } catch (NumberFormatException e) {
                    // Ігнорувати рядки, що не можуть бути перетворені на цілі числа
                    System.out.println("Пропускаю рядок: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
            return;
        }

        if (count > 0) {
            double average = (double) sum / count;

            List<Integer> lessThanAverage = new ArrayList<>();
            for (int number : numbers) {
                if (number < average) {
                    lessThanAverage.add(number);
                }
            }

            Collections.sort(lessThanAverage);

            // Вивести числа, менші за середнє, в файл
            String outputFilePathString = "D:\\java proj\\Main\\src\\менші_за_середнє.txt";
            Path outputFilePath = Paths.get(outputFilePathString);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePathString))) {
                for (int number : lessThanAverage) {
                    writer.write(String.valueOf(number));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Помилка при записі до файлу: " + e.getMessage());
            }

            // Вивести решту чисел на консоль
            System.out.println("Решта чисел:");
            for (int number : numbers) {
                if (number >= average) {
                    System.out.println(number);
                }
            }
        } else {
            System.out.println("У файлі немає чисел для обробки.");
        }
    }
}