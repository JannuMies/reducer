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

public class VisvalingamWhyattContainer<T extends Point> {

	private List<T> originalPointList;
	private LinkedList<RemovedPoint<T>> removedList;

	public VisvalingamWhyattContainer(List<T> pointList)
			throws InvalidReductionSizeException {
		if (pointList.size() < 2) {
			throw new InvalidReductionSizeException(
					"The given list contains less than two points.");
		}
		this.originalPointList = pointList;
		removedList = new LinkedList<RemovedPoint<T>>();
		doTheMagic(pointList);
	}

	public List<T> filterToSize(int size)
			throws InvalidReductionSizeException {
		if (size < 2) {
			throw new InvalidReductionSizeException(
					"Can not reduce a list to less than two points.");
		}
		if (size >= originalPointList.size()) {
			return originalPointList;
		}
		List<T> listToBeFiltered = new ArrayList<T>(originalPointList);
		List<T> pointsToRemove = getPointsToRemove(size);
		listToBeFiltered.removeAll(pointsToRemove);
		return listToBeFiltered;
	}

	private List<T> getPointsToRemove(int size) {
		ArrayList<T> removalList = new ArrayList<T>();
		int itemsRemovalCount = originalPointList.size() - size;
		Iterator<RemovedPoint<T>> iterator = removedList.iterator();
		while (removalList.size() < itemsRemovalCount) {
			removalList.add(iterator.next().getPoint());
		}
		return removalList;
	}

	private void doTheMagic(List<T> pointList) {
		Map<T, Triangle<T>> pointToTriangleMap = buildTriangleMap(pointList);
		while ((removedList.size() + 2) < pointList.size()) {
			Triangle<T> smallestTriangle = getSmallestTriangle(pointToTriangleMap);
			double removedArea = getLargerOfLatestRemovedAndCurrentTriangle(smallestTriangle);
			updateNeighbouringTriangles(smallestTriangle, pointToTriangleMap);
			T currentPoint = smallestTriangle.getCurrent();
			removedList.add(new RemovedPoint<T>(currentPoint, removedArea));
			pointToTriangleMap.remove(currentPoint);
		}
		Collections.sort(removedList, new RemovedPointComparator());
	}

	private void updateNeighbouringTriangles(Triangle<T> smallestTriangle,
			Map<T, Triangle<T>> pointToTriangleMap) {
		Triangle<T> last = pointToTriangleMap.get(smallestTriangle.getLast());
		Triangle<T> next = pointToTriangleMap.get(smallestTriangle.getNext());

		updateNextTriangle(last, smallestTriangle, next);
		updateLastTriangle(last, smallestTriangle, next);
	}

	private void updateLastTriangle(Triangle<T> last, Triangle<T> current,
			Triangle<T> next) {
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

	private void updateNextTriangle(Triangle<T> last, Triangle<T> current,
			Triangle<T> next) {
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
			Triangle<T> smallestTriangle) {
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

	private Triangle<T> getSmallestTriangle(Map<T, Triangle<T>> pointToTriangleMap) {
		double smallestArea = Double.MAX_VALUE;
		Triangle<T> smallestTriangle = null;
		for (Triangle<T> triangle : pointToTriangleMap.values()) {
			if (triangle.getArea() < smallestArea) {
				smallestArea = triangle.getArea();
				smallestTriangle = triangle;
			}
		}
		return smallestTriangle;
	}

	private Map<T, Triangle<T>> buildTriangleMap(List<T> pointList) {
		HashMap<T, Triangle<T>> map = new HashMap<T, Triangle<T>>(
				pointList.size());
		for (int i = 1; i < pointList.size() - 1; i++) {
			Triangle<T> triangle = new Triangle<T>(pointList.get(i - 1),
					pointList.get(i), pointList.get(i + 1));
			map.put(pointList.get(i), triangle);
		}
		return map;
	}
}
