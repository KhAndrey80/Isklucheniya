import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите через пробел данные в английской раскладке: 1. Фамилия 2. Имя 3. Отчество 4. дата _ рождения (формат yyyy-mm-dd) 5. номер _ телефона (только цифры) 6. пол(m/f)):");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                System.out.println("Введено неверное количество данных. Пожалуйста, введите 6 значений.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String secondName = data[2];
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(data[3]);
            } catch (DateTimeParseException e) {

                System.out.println("Неверный формат даты рождения. Используйте формат yyyy-mm-dd");
                return;
            }
            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат номера телефона. Используйте только цифры.");
                return;
            }
            char gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                System.out.println("Пол должен быть указан как f (женский) или m (мужской).");
                return;
            }

            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

                String output = lastName + " " + firstName + " " + secondName + " " +
                        dateOfBirth + " " +
                        phoneNumber + " " + gender;
                writer.write(output);
                writer.newLine();
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}