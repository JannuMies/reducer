package net.talviuni.reduce.visvalingamwhyatt;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.talviuni.reduce.EliminatedTestPoint;
import net.talviuni.reduce.InvalidReductionSizeException;
import net.talviuni.reduce.Point;
import net.talviuni.reduce.TestPoint;

import org.junit.Before;
import org.junit.Test;

public class VisvalingamWhyattContainerTest {

    private List<TestPoint> pointList;

    @Before
    public void setup() {
        pointList = new ArrayList<TestPoint>();
    }

    @Test
    public void ensureReducingTwoPointsWorks() throws Exception {
        pointList.add(new TestPoint(1, 0));
        pointList.add(new TestPoint(2, 2));
        VisvalingamWhyattContainer<TestPoint> container = new VisvalingamWhyattContainer<TestPoint>(
                pointList);

        List<TestPoint> filteredList = container.filterToSize(pointList.size());

        assertEquals(pointList.size(), filteredList.size());
    }

    @Test(expected = InvalidReductionSizeException.class)
    public void ensureConstructionContainerWithOnePointFails() throws Exception {
        pointList.add(new TestPoint(1, 0));

        new VisvalingamWhyattContainer<TestPoint>(pointList);
    }

    @Test
    public void ensureReducingThreePointListToThreePointsWorks() throws Exception {
        pointList.add(new TestPoint(1, 0));
        pointList.add(new TestPoint(2, 2));
        pointList.add(new TestPoint(3, 0));

        VisvalingamWhyattContainer<TestPoint> container = new VisvalingamWhyattContainer<TestPoint>(
                pointList);

        List<TestPoint> filteredList = container.filterToSize(pointList.size());

        assertEquals(pointList.size(), filteredList.size());
    }

    @Test
    public void testRemovingSmallestPointWorks() throws Exception {
        pointList.add(new TestPoint(1, 0));
        pointList.add(new TestPoint(2, 2));
        pointList.add(new EliminatedTestPoint(3, 1));
        pointList.add(new TestPoint(4, 2));
        VisvalingamWhyattContainer<TestPoint> container = new VisvalingamWhyattContainer<TestPoint>(
                pointList);

        List<TestPoint> filteredList = container.filterToSize(3);

        assertTrue(filteredList.containsAll(clearListOfEliminatedPoints(pointList)));
    }

    @Test
    public void testReFilteringRemovesPointsInExpectedOrder() throws Exception {
        TestPoint start = new TestPoint(0, 0);
        TestPoint secondToGo = new TestPoint(4, 0);
        TestPoint firstToGo = new TestPoint(5, 1);
        TestPoint staying = new TestPoint(5, 0);
        TestPoint end = new TestPoint(9, 0.1);

        pointList.add(start);
        pointList.add(secondToGo);
        pointList.add(firstToGo);
        pointList.add(staying);
        pointList.add(end);

        VisvalingamWhyattContainer<TestPoint> container = new VisvalingamWhyattContainer<TestPoint>(
                pointList);

        List<TestPoint> fourPointList = container.filterToSize(4);
        assertFalse(fourPointList.contains(firstToGo));
        assertEquals(4, fourPointList.size());

        List<TestPoint> threePointList = container.filterToSize(3);
        assertFalse(threePointList.contains(firstToGo));
        assertFalse(threePointList.contains(secondToGo));
        assertEquals(3, threePointList.size());
    }

    private Collection<? extends Point> clearListOfEliminatedPoints(List<? extends Point> pointList) {
        List<Point> list = new ArrayList<Point>();
        for (Point point : pointList) {
            if (!(point instanceof EliminatedTestPoint)) {
                list.add(point);
            }
        }
        return list;
    }
}
