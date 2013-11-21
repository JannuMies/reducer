package net.talviuni.reduce;

import java.util.List;

public interface Reducer {
    /**
     * Reduce a sequence of 2D points below a certain size.
     * 
     * @param pointList
     * @param size
     * @return
     */
    public List<Point> reduceBelowThreshold(List<Point> pointList, int size) throws InvalidReductionSizeException;
}
