import java.util.Scanner;

public class Service {
    public void hello(){
        System.out.println("Приветсвую проверяющего!");
    }
    public String[] requestData(){
        System.out.println("Введите, пожалуйста, через пробел следующие данные:");
        System.out.println("Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.split(" ");
    }
    public int dataCheck(String[] data){
        if (data.length <6){
            return 1;
        }
        else if (data.length >6){
            return 2;
        }
        return 0;
    }
}
