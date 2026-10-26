package gitok;

// Подключение библиотек или методов
import java.io.File;                    // Класс для определения файла для работы с ним
import java.io.FileNotFoundException;   // Исключение: файл не найден или нет доступа
import java.io.FileWriter;              // Класс для записи данных в файл
import java.io.IOException;             // Основной класс ошибки, связанной с записью/чтением данных
import java.io.PrintWriter;             // Класс для форматированной записи данных в файл
import java.util.ArrayList;             // Список, размер которого увеличивается сам
import java.util.Locale;                // Локаль, чтобы дробная часть всегда была через точку
import java.util.Scanner;               // Класс для считывания данных

//модуль для работы с текстовыми файлами
public class file_work {

    /// Функция для очистки файла (удаления данных из файла)
    /// Функция может вызвать исключение, если нет доступа к файлу
    /// @param fileName - имя файла
    static void clear_file(String fileName) {
        // Открытие файла в режиме перезаписи (false) без записи данных очищает его
        try (FileWriter writer = new FileWriter(fileName, false)) {
        } catch (IOException e) {   // Обработка исключения
            System.out.println("Ошибка очистки файла " + fileName);
        }
    }


    /// Функция для записи массива в файл (перезаписывает файл)
    /// Числа записываются через пробел, по 5 в строке
    /// @param fileName - имя файла
    /// @param arr - массив для записи
    static void write_arr(String fileName, double[] arr) {
        // Если массив пуст или не выделен - завершаем функцию
        if (arr == null || arr.length == 0) return;

        // try-with-resources: файл закроется автоматически, даже если произойдёт ошибка
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, false))) {
            for (int i = 0; i < arr.length; i++) {
                out.printf(Locale.US, "%.2f ", arr[i]);     // Запись элемента массива
                // Каждые 5 чисел меняем строку для удобного чтения
                if ((i + 1) % 5 == 0) {
                    out.println();
                }
            }
        } catch (IOException e) {   // Обработка исключения
            System.out.println("Ошибка записи в файл " + fileName);
        }
    }


    /// Функция для дозаписи массива и суммы квадратов в конец файла
    /// Вид записи: a_1^2 + a_2^2 + ... + a_n^2 = сумма
    /// @param fileName - имя файла
    /// @param arr - массив для записи
    static void append_arr_with_sum(String fileName, double[] arr) {
        // Если массив пуст или не выделен - завершаем функцию
        if (arr == null || arr.length == 0) return;

        // true - дозапись в конец файла, старые данные сохраняются
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, true))) {
            for (int i = 0; i < arr.length - 1; i++) {
                out.printf(Locale.US, "%.2f^2 + ", arr[i]);
                // Каждые 4 слагаемых меняем строку
                if ((i + 1) % 4 == 0) {
                    out.println();
                }
            }
            // Последний элемент записываем отдельно, чтобы в конце не было лишнего знака "+"
            out.printf(Locale.US, "%.2f^2 = %.2f;%n%n", arr[arr.length - 1], CalcArr.culc_arr(arr));
        } catch (IOException e) {   // Обработка исключения
            System.out.println("Ошибка записи в файл " + fileName);
        }
    }


    /// Функция для считывания массива из текстового файла
    /// Количество чисел в файле может быть любым
    /// @param fileName - имя файла
    /// @return либо считанный массив, либо пустой массив
    static double[] read_arr(String fileName) {
        ArrayList<Double> list = new ArrayList<>();     // Сюда складываем числа по мере чтения

        try (Scanner sc = new Scanner(new File(fileName))) {
            sc.useLocale(Locale.US);            // Дробная часть через точку
            while (sc.hasNextDouble()) {        // Пока впереди есть число
                list.add(sc.nextDouble());
            }
        } catch (FileNotFoundException e) {     // Файл не найден или нет доступа
            System.out.println("Файл для чтения не найден!");
            return new double[0];               // Возврат пустого массива
        }

        // Переносим числа из списка в обычный массив
        double[] arr = new double[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;     // Возврат заполненного массива
    }
}