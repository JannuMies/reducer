package net.talviuni.reduce;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class AssociatedTriangleTest {

    @Test
    public void testRightAngleTwoByTwoTriangleHasAreaOfTwo() {
        TestPoint first = new TestPoint(0, 0);
        TestPoint second = new TestPoint(0, 2);
        TestPoint third = new TestPoint(2, 2);
        AssociatedTriangle triangle = createTriangle(first, second, third);

        Assert.assertEquals(2, triangle.getArea(), 0.00005);
    }
    
    @Test
    public void testNonrightAngleTrianleWithOffset() {
        TestPoint first = new TestPoint(-1, -1);
        TestPoint second = new TestPoint(1, -1);
        TestPoint third = new TestPoint(5, 3);
        AssociatedTriangle triangle = createTriangle(first, second, third);

        Assert.assertEquals(4, triangle.getArea(), 0.00005);
    }

    private AssociatedTriangle createTriangle(TestPoint first, TestPoint second, TestPoint third) {
        ArrayList<Point> pointList = new ArrayList<Point>();
        pointList.add(first);
        pointList.add(second);
        pointList.add(third);
        return new AssociatedTriangle(pointList, 1);
    }

    private class TestPoint implements Point {

        private Double x;
        private Double y;

        public TestPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Double getX() {
            return x;
        }

        public Double getY() {
            return y;
        }
    }
}
