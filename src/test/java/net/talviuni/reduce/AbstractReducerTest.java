package net.talviuni.reduce;

import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;
import net.talviuni.reduce.EliminatedTestPoint;
import net.talviuni.reduce.Point;
import net.talviuni.reduce.TestPoint;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractReducerTest {

	private Reducer reducer;

	@Before
	public void setup() {
		reducer = getReducer();
	}

	public abstract Reducer getReducer();
	
	@Test
	public void ensureReducingReturnsNonNullValue() throws Exception {
		List<Point> reducedList = reducer.reduceBelowThreshold(
				new ArrayList<Point>(), 666);

		assertNotNull(reducedList);
	}

	@Test
	public void ensureSmallestTriangleIsRemoved() throws Exception {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(1, 0));
		pointList.add(new TestPoint(2, 2));
		pointList.add(new TestPoint(3, 0));
		pointList.add(new EliminatedTestPoint(4, 1));
		pointList.add(new TestPoint(5, 0));

		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		Assert.assertTrue(reducedList
				.containsAll(clearListOfEliminatedPoints(pointList)));
	}

	@Test
	public void ensureSmallestNeighbouringTrianglesAreRemoved()
			throws Exception {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(0, 0));
		pointList.add(new TestPoint(2, 2));
		pointList.add(new EliminatedTestPoint(3, 1.5));
		pointList.add(new EliminatedTestPoint(4, 1.2));
		pointList.add(new TestPoint(5, 2));
		pointList.add(new TestPoint(10, 0));

		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		Assert.assertTrue(reducedList
				.containsAll(clearListOfEliminatedPoints(pointList)));
	}

	@Test
	public void ensureRemovingSmallesNonNeighbouringTrianglesAreRemoved()
			throws Exception {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(new TestPoint(1, 0));
		pointList.add(new EliminatedTestPoint(2, 1.2));
		pointList.add(new TestPoint(3, 2));
		pointList.add(new EliminatedTestPoint(4, 0.8));
		pointList.add(new TestPoint(5, 0));

		List<Point> reducedList = reducer.reduceBelowThreshold(pointList, 4);

		Assert.assertTrue(reducedList
				.containsAll(clearListOfEliminatedPoints(pointList)));
	}

	private Collection<Point> clearListOfEliminatedPoints(
			ArrayList<Point> pointList) {
		ArrayList<Point> list = new ArrayList<Point>();
		for (Point point : pointList) {
			if (!(point instanceof EliminatedTestPoint)) {
				list.add(point);
			}
		}
		return list;
	}
}
