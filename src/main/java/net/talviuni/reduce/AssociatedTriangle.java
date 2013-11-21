package net.talviuni.reduce;

import java.util.List;

public class AssociatedTriangle {
    private Point previous;
    private Point current;
    private Point next;
    private double area;

    public AssociatedTriangle(List<Point> pointList, int index) {
        previous = pointList.get(index - 1);
        current = pointList.get(index);
        next = pointList.get(index + 1);
        area = calculateArea();
    }

    public double getArea() {
        return area;
    }
    
    public Point getCurrentPoint() {
    	return current;
    }
    
    private double calculateArea() {
        return Math.abs(
                (previous.getX() - next.getX()) * (current.getY() - previous.getY())
                - (previous.getX() - current.getX()) * (next.getY() - previous.getY())
                ) / 2;
    }
}
