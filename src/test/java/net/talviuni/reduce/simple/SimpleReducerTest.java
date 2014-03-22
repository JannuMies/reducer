package net.talviuni.reduce.simple;

import net.talviuni.reduce.AbstractReducerTest;
import net.talviuni.reduce.Reducer;

public class SimpleReducerTest extends AbstractReducerTest {

    @Override
    public Reducer getReducer() {
        return new SimpleReducer();
    }
}
