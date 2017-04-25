package eg.edu.alexu.csd.datastructure.calculator;

/**
 * Created by Bassam on 3/2/2017.
 */
public interface ICalculator {
    /**
     * Adds given two numbers
     *
     * @param x first number
     * @param y second number
     * @return the sum of the two numbers
     */
    int add(int x, int y);

    /**
     * Divids two numbers
     *
     * @param x first number
     * @param y second number
     * @return the division result
     */
    float divide(int x, int y);
}
