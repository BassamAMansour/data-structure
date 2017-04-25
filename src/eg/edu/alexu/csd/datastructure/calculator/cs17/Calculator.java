package eg.edu.alexu.csd.datastructure.calculator.cs17;

import eg.edu.alexu.csd.datastructure.calculator.ICalculator;

/**
 * Created by Bassam on 3/2/2017.
 */
public class Calculator implements ICalculator {

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public float divide(int x, int y) {
        return (float) x / y;
    }
}
