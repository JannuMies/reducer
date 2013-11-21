package net.talviuni.reduce;

import java.util.List;

public interface Reducer {
    /**
     * Reduce a list of 2D points below a certain threshold.
     * 
     * @param pointList
     * @param threshold
     * @return
     */
    public List<Point> reduceBelowThreshold(List<Point> pointList, int threshold);
}
