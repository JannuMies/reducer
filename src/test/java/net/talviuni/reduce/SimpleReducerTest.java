package net.talviuni.reduce;

import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;

public class SimpleReducerTest {

    @Test
    public void testReducingReturnsNonNullValue() {
        Reducer reducer = new SimpleReducer();
        
        List<Point> reducedList = reducer.reduceBelowThreshold(null, 666);
        
        assertNotNull(reducedList);
    }
}
