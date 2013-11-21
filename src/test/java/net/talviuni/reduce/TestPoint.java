package net.talviuni.reduce;

public class TestPoint implements Point {

    private double y;
    private double x;

    public TestPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    @Override
    public String toString() {
    	return "X " + x + " - Y " + y;
    }
}