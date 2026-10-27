package Zabgu;

/**
 * Треугольник, заданный координатами трёх вершин A, B, C на плоскости.
 * Треугольник не может быть вырожденным (вершины не лежат на одной прямой).
 */
public class Triangle {
    private double ax, ay;   // координаты вершины A
    private double bx, by;   // координаты вершины B
    private double cx, cy;   // координаты вершины C

    /** Конструктор без параметров: треугольник (0,0), (1,0), (0,1). */
    public Triangle() {
        ax = 0; ay = 0;
        bx = 1; by = 0;
        cx = 0; cy = 1;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param ax1
     * @param ay1
     * @param bx1
     * @param by1
     * @param cx1
     * @param cy1
     * @throws IllegalArgumentException
     */
    public Triangle(double ax1, double ay1, double bx1, double by1, double cx1, double cy1)
            throws IllegalArgumentException {
        if (line(ax1, ay1, bx1, by1, cx1, cy1))   // проверка предусловия
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        ax = ax1; ay = ay1;
        bx = bx1; by = by1;
        cx = cx1; cy = cy1;
    }

    /**
     * Вспомогательный метод: проверяет, лежат ли три точки на одной прямой.
     *
     * @param ax1
     * @param ay1
     * @param bx1
     * @param by1
     * @param cx1
     * @param cy1
     * @return
     */
    private boolean line(double ax1, double ay1, double bx1, double by1, double cx1, double cy1) {
        double s = Math.abs((bx1 - ax1) * (cy1 - ay1) - (cx1 - ax1) * (by1 - ay1)) / 2;
        return s < 0.000001;
    }

    //Геттеры

    /**
     * Геттер координаты x вершины A.
     * @return
     */
    public double getAx() { return ax; }

    /**
     * Геттер координаты y вершины A.
     * @return
     */
    public double getAy() { return ay; }

    /**
     * Геттер координаты x вершины B.
     * @return
     */
    public double getBx() { return bx; }

    /**
     * Геттер координаты y вершины B.
     * @return
     */
    public double getBy() { return by; }

    /**
     * Геттер координаты x вершины C.
     * @return
     */
    public double getCx() { return cx; }

    /**
     * Геттер координаты y вершины C.
     * @return
     */
    public double getCy() { return cy; }

    //Сеттеры
    // Каждый сеттер проверяет, не лягут ли вершины на одну прямую с новым значением.
    // Если лягут, бросает IllegalArgumentException, и поле не меняется.

    /**
     * Сеттер координаты x вершины A.
     * @param x
     * @throws IllegalArgumentException
     */
    public void setAx(double x) throws IllegalArgumentException {
        if (line(x, ay, bx, by, cx, cy))   // проверка предусловия
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        ax = x;
    }

    /**
     * Сеттер координаты y вершины A.
     * @param y
     * @throws IllegalArgumentException
     */
    public void setAy(double y) throws IllegalArgumentException {
        if (line(ax, y, bx, by, cx, cy))
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        ay = y;
    }

    /**
     * Сеттер координаты x вершины B.
     * @param x
     * @throws IllegalArgumentException
     */
    public void setBx(double x) throws IllegalArgumentException {
        if (line(ax, ay, x, by, cx, cy))
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        bx = x;
    }

    /**
     * Сеттер координаты y вершины B.
     * @param y
     * @throws IllegalArgumentException
     */
    public void setBy(double y) throws IllegalArgumentException {
        if (line(ax, ay, bx, y, cx, cy))
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        by = y;
    }

    /**
     * Сеттер координаты x вершины C.
     * @param x
     * @throws IllegalArgumentException
     */
    public void setCx(double x) throws IllegalArgumentException {
        if (line(ax, ay, bx, by, x, cy))
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        cx = x;
    }

    /**
     * Сеттер координаты y вершины C.
     * @param y
     * @throws IllegalArgumentException
     */
    public void setCy(double y) throws IllegalArgumentException {
        if (line(ax, ay, bx, by, cx, y))
            throw new IllegalArgumentException("Вершины лежат на одной прямой");
        cy = y;
    }

    //Вычисляемые методы

    /**
     * Длина стороны AB.
     * @return
     */
    public double sideAB() {
        return Math.sqrt((bx - ax) * (bx - ax) + (by - ay) * (by - ay));
    }

    /**
     * Длина стороны BC.
     * @return
     */
    public double sideBC() {
        return Math.sqrt((cx - bx) * (cx - bx) + (cy - by) * (cy - by));
    }

    /**
     * Длина стороны CA.
     * @return
     */
    public double sideCA() {
        return Math.sqrt((ax - cx) * (ax - cx) + (ay - cy) * (ay - cy));
    }

    /**
     * Периметр: сумма длин трёх сторон.
     * @return
     */
    public double perimeter() {
        return sideAB() + sideBC() + sideCA();
    }

    /**
     * Площадь по координатам вершин.
     * @return
     */
    public double area() {
        return Math.abs((bx - ax) * (cy - ay) - (cx - ax) * (by - ay)) / 2;
    }

    /**
     * Проверка на равенство: true, если все координаты совпадают.
     * @param t
     * @return
     */
    public boolean equals(Triangle t) {
        return ax == t.ax && ay == t.ay
                && bx == t.bx && by == t.by
                && cx == t.cx && cy == t.cy;
    }

    /**
     * Строка с описанием треугольника.
     * @return
     */
    public String toString() {
        return String.format("A(%.2f; %.2f) B(%.2f; %.2f) C(%.2f; %.2f)", ax, ay, bx, by, cx, cy);
    }
}