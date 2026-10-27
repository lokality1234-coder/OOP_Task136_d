package Zabgu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Создание объекта вызов методов
        Triangle t1 = new Triangle(0, 0, 4, 0, 0, 3);
        System.out.println(t1);
        System.out.println("Периметр: " + t1.perimeter());   // 12.0
        System.out.println("Площадь: " + t1.area());         // 6.0

        //Сеттер с проверкой
        try {
            t1.setCy(0);   // все вершины окажутся на одной прямой
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        //Конструктор с неверными данными
        try {
            Triangle bad = new Triangle(0, 0, 1, 1, 2, 2);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        //Сравнение на равенство
        Triangle t2 = new Triangle(0, 0, 4, 0, 0, 3);
        System.out.println("t1 == t2: " + (t1 == t2));         // false (разные объекты)
        System.out.println("t1.equals(t2): " + t1.equals(t2)); // true (координаты совпали)

        //Массив из 3 ссылок (объекты пока null)
        Triangle[] arr = new Triangle[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Triangle();   // здесь вызывается конструктор
        }
        arr[0].setBx(5);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //ArrayList с памятью на 10 элементов
        ArrayList<Triangle> list = new ArrayList<Triangle>(10);
        for (int i = 1; i <= 5; i++) {
            list.add(new Triangle(0, 0, i, 0, 0, i));   // конструктор вызывается при каждом new
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + "  S=" + list.get(i).area());
        }
    }
}