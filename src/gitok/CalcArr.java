package gitok;

//подключение типа (класса) для генерации случайного числа
import java.util.Random;
//подключение типа (класса) для чтения данных с клавиатуры
import java.util.Scanner;

//объявление класса
public class CalcArr {

    /**
     * Функция заполнения массива случайными числами в диапазоне [min, max]
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static double[] random_arr(int length, double min, double max) {
        //если min не меньше max, диапазон некорректный, бросаем исключение
        if (min >= max) {
            throw new IllegalArgumentException("min должен быть меньше max");
        }
        Random rnd = new Random();
        double[] arr = new double[length];//new - выделение памяти под массив
        for (int i = 0; i < length; i++) { //length - переменная отвечающая за длинну массива
            arr[i] = rnd.nextDouble(min, max);
        }
        return arr; //возврат заполненого массива
    }


    /**
     * Функция для заполнения массива вручную (пользователем с клавиатуры)
     * @param length
     * @param in - объект для чтения с клавиатуры
     * @return
     */
    public static double[] user_arr(int length, Scanner in) {
        double[] arr = new double[length];
        for (int i = 0; i < length; i++) {
            System.out.printf("x_%d = ", i + 1);
            arr[i] = in.nextDouble();
        }
        return arr; //возврат заполненого массива
    }


    /**
     * Функция выбора пользователя по заполнению массива
     * @param in - объект для чтения с клавиатуры
     * @return - либо 1, либо 0
     */
    static int input_selection(Scanner in) {
        int var = -1;//переменная для выбора пользователя
        System.out.println("Выберите способ заполнения массива");
        System.out.println("0 - массив заполнится автоматически \n1 - массив заполняете вы");
        while (var != 0 && var != 1) {//цикл пока не будет введено 0 или 1
            System.out.println("Введите число 1 или 0");
            var = in.nextInt();//in.nextInt() - метод который считывает с клавиатуры целое число типа int
        }
        return var;
    }


    /**
     * Функция выбора способа заполнения массива
     * @param length
     * @param var
     * @param in
     * @return
     */
    public static double[] fill_arr(int length, int var, Scanner in) {
        if (var == 1) {                 //если 1 то пользовательн заполняет сам
            return user_arr(length, in);
        }
        //иначе заполняется рандомно, диапазон вводит пользователь
        System.out.print("Введите min: ");
        double min = in.nextDouble();
        System.out.print("Введите max: ");
        double max = in.nextDouble();
        return random_arr(length, min, max);
    }


    /**
     * Вывод массива на экран пользователя
     * @param arr
     */
    public static void print_arr(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%6.2f ", arr[i]);

            //каждые 5 цифр меняем строку
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        //перевод строки после вывода массива
        if (arr.length % 5 != 0) {
            System.out.println();
        }
    }

    /**
     * Вычисление суммы квадратов
     * @param arr
     * @return
     */
    public static double culc_arr(double[] arr) {
        //объявление переменной суммы типа double
        double sum = 0;
        //цикл вычисления последовательности а_1^2 + a_2^2 ... a_n^2
        for (int i = 0; i < arr.length; i++) {
            sum += Math.pow(arr[i], 2);//запись каждого элемента последовательности к сумме
        }
        return sum;//добавить бросок исключения!!!!!!!!!!!
    }

    /// Функция для проверки вычислений функции culc_arr
    static void assert_tests() {
        //assert - если условие ложно, программа выводит сообщение об ошибке
        //работает только с флагом запуска -ea
        assert Math.abs(culc_arr(new double[]{1, 1, 1}) - 3.0) < 0.001 : "Ошибка вычисления функции";
        assert Math.abs(culc_arr(new double[]{0, 0, 0}) - 0.0) < 0.001 : "Ошибка вычисления функции";
        assert Math.abs(culc_arr(new double[]{1, 2, 3}) - 14.0) < 0.001 : "Ошибка вычисления функции";
    }
}