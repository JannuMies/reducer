package net.talviuni.reduce.visvalingamwhyatt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.talviuni.reduce.InvalidReductionSizeException;
import net.talviuni.reduce.Point;

public class VisvalingamWhyattContainer {

	private List<Point> originalPointList;
	private LinkedList<RemovedPoint> removedList;

	public VisvalingamWhyattContainer(List<Point> pointList)
			throws InvalidReductionSizeException {
		if (pointList.size() < 2) {
			throw new InvalidReductionSizeException(
					"The given list contains less than two points.");
		}
		this.originalPointList = pointList;
		removedList = new LinkedList<RemovedPoint>();
		doTheMagic(pointList);
	}

	public List<Point> filterToSize(int size)
			throws InvalidReductionSizeException {
		if (size < 2) {
			throw new InvalidReductionSizeException(
					"Can not reduce a list to less than two points.");
		}
		if (size >= originalPointList.size()) {
			return originalPointList;
		}
		List<Point> listToBeFiltered = new ArrayList<Point>(originalPointList);
		List<Point> pointsToRemove = getPointsToRemove(size);
		listToBeFiltered.removeAll(pointsToRemove);
		return listToBeFiltered;
	}

	private List<Point> getPointsToRemove(int size) {
		ArrayList<Point> removalList = new ArrayList<Point>();
		int itemsRemovalCount = originalPointList.size() - size;
		Iterator<RemovedPoint> iterator = removedList.iterator();
		while (removalList.size() < itemsRemovalCount) {
			removalList.add(iterator.next().getPoint());
		}
		return removalList;
	}

	private void doTheMagic(List<Point> pointList) {
		Map<Point, Triangle> pointToTriangleMap = buildTriangleMap(pointList);
		while ((removedList.size() + 2) < pointList.size()) {
			Triangle smallestTriangle = getSmallestTriangle(pointToTriangleMap);
			double removedArea = getLargerOfLatestRemovedAndCurrentTriangle(smallestTriangle);
			updateNeighbouringTriangles(smallestTriangle, pointToTriangleMap);
			Point currentPoint = smallestTriangle.getCurrent();
			removedList.add(new RemovedPoint(currentPoint, removedArea));
			pointToTriangleMap.remove(currentPoint);
		}
		Collections.sort(removedList, new RemovedPointComparator());
	}

	private void updateNeighbouringTriangles(Triangle smallestTriangle,
			Map<Point, Triangle> pointToTriangleMap) {
		Triangle last = pointToTriangleMap.get(smallestTriangle.getLast());
		Triangle next = pointToTriangleMap.get(smallestTriangle.getNext());

		updateNextTriangle(last, smallestTriangle, next);
		updateLastTriangle(last, smallestTriangle, next);
	}

	private void updateLastTriangle(Triangle last, Triangle current,
			Triangle next) {
		if (null == last) {
			return;
		}
		if (null == next) {
			last.setNext(current.getNext());
		} else {
			last.setNext(next.getCurrent());
		}
		last.updateArea();
	}

	private void updateNextTriangle(Triangle last, Triangle current,
			Triangle next) {
		if (null == next) {
			return;
		}
		if (null == last) {
			next.setLast(current.getLast());
		} else {
			next.setLast(last.getCurrent());
		}
		next.updateArea();
	}

	private double getLargerOfLatestRemovedAndCurrentTriangle(
			Triangle smallestTriangle) {
		if (removedList.isEmpty()) {
			return smallestTriangle.getArea();
		}
		double lastRemovedArea = removedList.getLast().getArea();
		if (lastRemovedArea < smallestTriangle.getArea()) {
			return smallestTriangle.getArea();
		} else {
			return lastRemovedArea;
		}
	}

	private Triangle getSmallestTriangle(Map<Point, Triangle> pointToTriangleMap) {
		double smallestArea = Double.MAX_VALUE;
		Triangle smallestTriangle = null;
		for (Triangle triangle : pointToTriangleMap.values()) {
			if (triangle.getArea() < smallestArea) {
				smallestArea = triangle.getArea();
				smallestTriangle = triangle;
			}
		}
		return smallestTriangle;
	}

	private Map<Point, Triangle> buildTriangleMap(List<Point> pointList) {
		HashMap<Point, Triangle> map = new HashMap<Point, Triangle>(
				pointList.size());
		for (int i = 1; i < pointList.size() - 1; i++) {
			Triangle triangle = new Triangle(pointList.get(i - 1),
					pointList.get(i), pointList.get(i + 1));
			map.put(pointList.get(i), triangle);
		}
		return map;
	}
}
