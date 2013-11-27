package net.talviuni.reduce.visvalingamwhyatt;

import net.talviuni.reduce.Point;
import net.talviuni.reduce.TriangleAreaCalculator;

public class Triangle {

	private Point last;
	private final Point current;
	private Point next;
	private double area;

	public Triangle(Point last, Point current, Point next) {
		this.last = last;
		this.current = current;
		this.next = next;
		updateArea();
	}

	public Point getLast() {
		return last;
	}

	public void setLast(Point last) {
		this.last = last;
	}

	public Point getNext() {
		return next;
	}

	public void setNext(Point next) {
		this.next = next;
	}

	public double getArea() {
		return area;
	}

	public void updateArea() {
		area = TriangleAreaCalculator.calculateArea(last, getCurrent(), next);
	}

	public Point getCurrent() {
		return current;
	}
}
