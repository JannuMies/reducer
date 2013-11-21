package net.talviuni.reduce;

import java.util.List;

public interface Reducer {
	public List<Point> reduceBelowThreshold(List<Point> pointList, int threshold);
}
