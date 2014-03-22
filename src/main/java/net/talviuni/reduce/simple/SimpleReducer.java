package net.talviuni.reduce.simple;

import java.util.ArrayList;
import java.util.List;

import net.talviuni.reduce.InvalidReductionSizeException;
import net.talviuni.reduce.Point;
import net.talviuni.reduce.Reducer;
import net.talviuni.reduce.TriangleAreaCalculator;

/**
 * Not a proper implementation of Visvalingam-Whyatt, just something to get the
 * supporting classes and a bunch of tests up.
 */
public class SimpleReducer implements Reducer {

    public <T extends Point> List<T> reduceBelowThreshold(List<T> pointList, int size)
            throws InvalidReductionSizeException {
        if (size < 2) {
            throw new InvalidReductionSizeException(
                    "Can not reduce a sequence to less than 2 points.");
        }
        if (pointList.size() <= size) {
            return pointList;
        }

        ArrayList<T> listToReduce = new ArrayList<T>(pointList);

        while (listToReduce.size() > size) {
            removeSmallestArea(listToReduce);
        }

        return listToReduce;
    }

    private <T extends Point> void removeSmallestArea(ArrayList<T> triangleList) {
        int indexOfSmallest = 0;
        double smallestArea = Double.MAX_VALUE;

        for (int i = 1; i < triangleList.size() - 1; i++) {
            double area = TriangleAreaCalculator.calculateArea(triangleList.get(i - 1),
                    triangleList.get(i), triangleList.get(i + 1));
            if (area < smallestArea) {
                indexOfSmallest = i;
                smallestArea = area;
            }

        }
        triangleList.remove(indexOfSmallest);
    }
}