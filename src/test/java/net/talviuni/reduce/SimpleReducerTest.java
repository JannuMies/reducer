package net.talviuni.reduce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;
import static junit.framework.Assert.*;

import org.junit.Test;

public class SimpleReducerTest {

	@Test
	public void ensureReducingReturnsNonNullValue() {
		Reducer reducer = new SimpleReducer();

		List<Point> reducedList = reducer.reduceBelowThreshold(
				new ArrayList<Point>(), 666);

		assertNotNull(reducedList);
	}

	@Test
	public void ensureReducingFivePointRouteByOneRemovesSmallestTriangle() {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(1, 0));
		pointList.add(new TestPoint(2, 2));
		pointList.add(new TestPoint(3, 0));
		pointList.add(new EliminatedTestPoint(4, 1)); // This one should go.
		pointList.add(new TestPoint(5, 0));

		SimpleReducer reducer = new SimpleReducer();
		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		Assert.assertTrue(reducedList.containsAll(clearListOfEliminatedPoints(pointList)));
	}

	@Test
	public void ensureReducingSixPointRouteByTwoRemovesSmallestNeighbouringTriangles() {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(0, 0));
		pointList.add(new TestPoint(2, 2));
		pointList.add(new EliminatedTestPoint(3, 1.5));
		pointList.add(new EliminatedTestPoint(4, 1.2));
		pointList.add(new TestPoint(5, 2));
		pointList.add(new TestPoint(10, 0));

		SimpleReducer reducer = new SimpleReducer();
		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		
		Assert.assertTrue(reducedList.containsAll(clearListOfEliminatedPoints(pointList)));
	}

	@Test
	public void ensureReducingFivePointRouteByTwoRemovesSmallestNonNeighbouringTriangles() {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(1, 0));
		pointList.add(new EliminatedTestPoint(2, 1.2)); // This one should go.
		pointList.add(new TestPoint(3, 2));
		pointList.add(new EliminatedTestPoint(4, 0.8)); // This one should go.
		pointList.add(new TestPoint(5, 0));

		SimpleReducer reducer = new SimpleReducer();
		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		Assert.assertTrue(reducedList.containsAll(clearListOfEliminatedPoints(pointList)));
	}
	
	private Collection<Point> clearListOfEliminatedPoints(ArrayList<Point> pointList) {
		ArrayList<Point> list = new ArrayList<Point>();
		for(Point point : pointList) {
			if(!(point instanceof EliminatedTestPoint)) {
				list.add(point);
			} 
		}
		return list;
	}
	
	private class EliminatedTestPoint extends TestPoint {

		public EliminatedTestPoint(double x, double y) {
			super(x, y);
		}
	}
}
