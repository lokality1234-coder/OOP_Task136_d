//Автор: Федурин Никита Иннокентьевич
//Задача N136_d
//пакеты позволяют ограничить классы логически в наборы, позволяет избежать ошибок между именами классов
package gitok;

import java.util.InputMismatchException;// Импорт исключения, которое возникает при вводе данных неверного типа
import java.util.Scanner;//подключение типа (класса) для чтения данных с клавиатуры

//Обьявление класса, в Java всё находить внутри классов
//public - значит класс доступен извне, "N136_d" - имя класса,должна совпадать в именем файла
public class N136_d {

    /// Функция вывода справки по использованию программы
    static void printHelp() {
        System.out.println("  Справка:");
        System.out.println("  Программа находит сумму квадратов a_1^2 + ... + a_n^2");
        System.out.println("  java N136_d               - ввод данных через консоль");
        System.out.println("  java N136_d -h            - вывод этой справки");
        System.out.println("  java N136_d --help        - вывод этой справки");
    }

    //главный метод, String[] args - массив строк, переданных при запуске программы
    public static void main(String[] args) {

        CalcArr.assert_tests();//проверка функции вычисления

        //проходим по всем аргументам командной строки
        for (String arg : args) {
            //если среди них есть -h или --help
            if (arg.equals("-h") || arg.equals("--help")) {
                printHelp(); //выводим справку
                return;//завершаем
            }
        }

        //часть кода в которой может быть ошибка
        try {
            Scanner in = new Scanner(System.in);//Создание обьекта для ввода с клавиатуры
            System.out.print("Введите значение n: ");

            int n = in.nextInt();//считывание числа n с клавиатуры

            int var = CalcArr.input_selection(in);

            //массив создаётся в функции и возвращается в N136_d
            double[] arr = CalcArr.fill_arr(n, var, in);

            System.out.println("Заполненный массив:");
            CalcArr.print_arr(arr);

            //вычисление суммы квадратов и вывод её на экран
            System.out.printf("Сумма квадратов: %.2f%n", CalcArr.culc_arr(arr));

            //если нашлась ошибка InputMismatchException то выводит сообщение
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: необходимо вводить только числа.");
        } catch (NegativeArraySizeException e) {
            System.out.println("Ошибка: необходимо вводить только положительное число n.");
        }
    }
}