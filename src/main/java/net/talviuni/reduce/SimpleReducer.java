package net.talviuni.reduce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Not a proper implementation of Visvalingam-Whyatt, just something to get
 * the supporting classes and a bunch of tests up.
 */
public class SimpleReducer implements Reducer {

	public List<Point> reduceBelowThreshold(List<Point> pointList, int threshold) {
		if(pointList.size() <= threshold) {
			return pointList;
		}
	    Point first = pointList.get(0);
	    Point last = pointList.get(pointList.size()-1);
	    
	    LinkedList<AssociatedTriangle> triangleList = new LinkedList<AssociatedTriangle>();
	    for(int i = 1; i < pointList.size()-1; i++) {
	    	triangleList.add(new AssociatedTriangle(pointList, i));
	    }
	    
	    while(triangleList.size() > threshold - 2 ) {
	    	removeSmallestArea(triangleList);
	    }
	    ArrayList<Point> reducedList = new ArrayList<Point>();
	    reducedList.add(first);
	    for(AssociatedTriangle triangle : triangleList) {
	    	reducedList.add(triangle.getCurrentPoint());
	    }
	    reducedList.add(last);
		return reducedList;
	}

	private void removeSmallestArea(LinkedList<AssociatedTriangle> triangleList) {
		AssociatedTriangle smallestTriangle = triangleList.get(0);
		for(AssociatedTriangle triangle : triangleList) {
			if(smallestTriangle.getArea() > triangle.getArea()) {
				smallestTriangle = triangle;
			}
		}
		triangleList.remove(smallestTriangle);
	}

}