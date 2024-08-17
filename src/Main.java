import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.hello();
        String[] data;

        String lastName;
        String firstName;
        String middleName;
        LocalDate birthDate = null;
        int phoneNums = 0;
        String gender;

        while (true) {
            data = service.requestData();
            if (service.dataCheck(data) == 1) {
                System.err.println("Вы ввели менее 6 параметров! Повторите ввод!");
            } else if (service.dataCheck(data) == 2) {
                System.err.println("Вы ввели больше 6 параметров! Повторите ввод!");
            } else if (service.dataCheck(data) == 0) {
                lastName = data[0];
                firstName = data[1];
                middleName = data[2];

                boolean flag = true;

                try {
                    DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    birthDate = LocalDate.parse(data[3], dataFormat);
                } catch (DateTimeException e){
                    System.err.println("Неверный формат даты! Требуется *dd.MM.yyyy*! Повторите ввод!");
                    flag = false;
                }

                try {
                    phoneNums = Integer.parseInt(data[4]);
                } catch (NumberFormatException e){
                    System.err.println("Неверный формат номера телефона! Повторите ввод!");
                    flag = false;
                }
                gender = data[5];
                if (!"m".equals(gender) && !"f".equals(gender)){
                    System.err.println("Неверный формат пола, введите f или m! Повторите ввод!");
                    flag = false;
                }
                if (flag){
                    break;
                }
                else {
                    continue;
                }

            }
        }

        String fileName = lastName+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(lastName+" "+firstName+" "+middleName+" "+birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE)+" "+phoneNums+" "+gender);
            writer.newLine();
        }catch (IOException e){
            System.err.println("Во время записи файла произошла ошибка!");
        }

    }
}