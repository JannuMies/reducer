package net.talviuni.reduce;

import junit.framework.Assert;

import org.junit.Test;

public class TriangleAreaCalculatorTest {

    @Test
    public void testRightAngleTwoByTwoTriangleHasAreaOfTwo() {
        TestPoint first = new TestPoint(0, 0);
        TestPoint second = new TestPoint(0, 2);
        TestPoint third = new TestPoint(2, 2);
        
        double area = TriangleAreaCalculator.calculateArea(first, second, third);

        Assert.assertEquals(2, area, 0.00005);
    }

    @Test
    public void testNonrightAngleTrianleWithOffset() {
        TestPoint first = new TestPoint(-1, -1);
        TestPoint second = new TestPoint(1, -1);
        TestPoint third = new TestPoint(5, 3);
        
        double area = TriangleAreaCalculator.calculateArea(first, second, third);

        Assert.assertEquals(4, area, 0.00005);
    }
}
