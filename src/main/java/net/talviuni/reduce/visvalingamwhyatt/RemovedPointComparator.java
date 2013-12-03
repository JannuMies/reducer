package net.talviuni.reduce.visvalingamwhyatt;

import java.util.Comparator;

public class RemovedPointComparator implements Comparator<RemovedPoint<?>>{

	public int compare(RemovedPoint<?> o1, RemovedPoint<?> o2) {
		return Double.compare(o1.getArea(), o2.getArea());
	}

}
