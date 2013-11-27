package net.talviuni.reduce.visvalingamwhyatt;

import java.util.List;

import net.talviuni.reduce.InvalidReductionSizeException;
import net.talviuni.reduce.Point;
import net.talviuni.reduce.Reducer;

public class VisvalingamWhyattReducer implements Reducer {

	public List<Point> reduceBelowThreshold(List<Point> pointList, int size)
			throws InvalidReductionSizeException {
		if(size < 2) {
			throw new InvalidReductionSizeException("Can not reduce a sequence to less than 2 points.");
		}
		if (pointList.size() <= size) {
			return pointList;
		}
		
		VisvalingamWhyattContainer container = new VisvalingamWhyattContainer(pointList);
		return container.filterToSize(size);
	}

}
