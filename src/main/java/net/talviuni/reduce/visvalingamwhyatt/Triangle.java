package net.talviuni.reduce.visvalingamwhyatt;

import net.talviuni.reduce.Point;
import net.talviuni.reduce.TriangleAreaCalculator;

public class Triangle<T extends Point> {

    private T last;
    private final T current;
    private T next;
    private double area;

    public Triangle(T last, T current, T next) {
        this.last = last;
        this.current = current;
        this.next = next;
        updateArea();
    }

    public T getLast() {
        return last;
    }

    public void setLast(T last) {
        this.last = last;
    }

    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }

    public double getArea() {
        return area;
    }

    public void updateArea() {
        area = TriangleAreaCalculator.calculateArea(last, getCurrent(), next);
    }

    public T getCurrent() {
        return current;
    }
}
