package net.talviuni.reduce.visvalingamwhyatt;

import net.talviuni.reduce.Point;

public class RemovedPoint {
	private final double area;
	private final Point point;

	public RemovedPoint(Point point, double area) {
		this.point = point;
		this.area = area;
	}

	public double getArea() {
		return area;
	}

	public Point getPoint() {
		return point;
	}
}
