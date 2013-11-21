package net.talviuni.reduce;

public class TriangleAreaCalculator {

	public static double calculateArea(Point first, Point second, Point third) {
		return Math.abs((first.getX() - third.getX())
				* (second.getY() - first.getY())
				- (first.getX() - second.getX())
				* (third.getY() - first.getY())) / 2;
	}
}
