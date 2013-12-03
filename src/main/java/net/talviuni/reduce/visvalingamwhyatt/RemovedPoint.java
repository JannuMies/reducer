package net.talviuni.reduce.visvalingamwhyatt;

import net.talviuni.reduce.Point;

public class RemovedPoint<T extends Point> {
	private final double area;
	private final T point;

	public RemovedPoint(T point, double area) {
		this.point = point;
		this.area = area;
	}

	public double getArea() {
		return area;
	}

	public T getPoint() {
		return point;
	}
}
