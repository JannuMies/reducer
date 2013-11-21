package net.talviuni.reduce;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import static junit.framework.Assert.*;

import org.junit.Test;

public class SimpleReducerTest {

    @Test
    public void ensureReducingReturnsNonNullValue() {
        Reducer reducer = new SimpleReducer();
        
        List<Point> reducedList = reducer.reduceBelowThreshold(new ArrayList<Point>(), 666);
        
        assertNotNull(reducedList);
    }
    
    @Test
    public void ensureReducingFourPointRouteToThreeRemovesLargestTriangle() {
        ArrayList<Point> pointList = new ArrayList<Point>();
        pointList.add(new TestPoint(1, 0));
        pointList.add(new TestPoint(2, 2));
        pointList.add(new TestPoint(3, 0));
        pointList.add(new TestPoint(4, 1)); // This one should go.
        pointList.add(new TestPoint(5, 0));
        
        SimpleReducer reducer = new SimpleReducer();
        List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);
        
        pointList.remove(3);
        Assert.assertTrue(reducedList.containsAll(pointList));
    }
}
