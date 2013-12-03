package net.talviuni.reduce;

public class TriangleAreaCalculator {

	public static <T extends Point> double calculateArea(T first, T second, T third) {
		return Math.abs((first.getX() - third.getX())
				* (second.getY() - first.getY())
				- (first.getX() - second.getX())
				* (third.getY() - first.getY())) / 2;
	}
}
