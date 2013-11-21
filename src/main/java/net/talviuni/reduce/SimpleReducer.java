package net.talviuni.reduce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Not a proper implementation of Visvalingam-Whyatt, just something to get the
 * supporting classes and a bunch of tests up.
 */
public class SimpleReducer implements Reducer {

	public List<Point> reduceBelowThreshold(List<Point> pointList, int threshold) {
		if (pointList.size() <= threshold) {
			return pointList;
		}

		LinkedList<Point> listToReduce = new LinkedList<Point>(pointList);

		while (listToReduce.size() > threshold) {
			removeSmallestArea(listToReduce);
		}

		return listToReduce;
	}

	private void removeSmallestArea(LinkedList<Point> triangleList) {
		int indexOfSmallest = 0;
		double smallestArea = Double.MAX_VALUE;

		for (int i = 1; i < triangleList.size() - 1; i++) {
			double area = TriangleAreaCalculator.calculateArea(
					triangleList.get(i - 1), triangleList.get(i),
					triangleList.get(i + 1));
			if (area < smallestArea) {
				indexOfSmallest = i;
				smallestArea = area;
			}

		}
		triangleList.remove(indexOfSmallest);
	}
}