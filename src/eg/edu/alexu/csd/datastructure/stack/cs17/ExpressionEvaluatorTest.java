package eg.edu.alexu.csd.datastructure.stack.cs17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Bassam on 4/20/2017.
 */
public class ExpressionEvaluatorTest {

    String expression;
    ExpressionEvaluator expressionEvaluator;

    @Before
    public void setUp() throws Exception {
        expression = " 6 2 / 3 - 4 2 * +";
        expressionEvaluator = new ExpressionEvaluator();
    }

    @Test
    public void infixToPostfix() throws Exception {

        Assert.assertEquals("a b c + * d *",expressionEvaluator.infixToPostfix("a * (b + c) * d"));
        Assert.assertEquals("2 3 4 * +",expressionEvaluator.infixToPostfix("2 + 3 * 4"));
        Assert.assertEquals("a b * 5 +",expressionEvaluator.infixToPostfix("a * b + 5"));
        Assert.assertEquals("1 2 + 7 *",expressionEvaluator.infixToPostfix("(1 + 2) * 7"));
        Assert.assertEquals("a b * c /",expressionEvaluator.infixToPostfix("a * b / c"));
        Assert.assertEquals("a b c - d + / e a - * c *",expressionEvaluator.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
        Assert.assertEquals("a b / c - d e * + a c * -",expressionEvaluator.infixToPostfix("a / b - c + d * e - a * c"));




    }

    @Test
    public void evaluate() throws Exception {
        Assert.assertEquals(8, expressionEvaluator.evaluate(expression));
        Assert.assertEquals(14, expressionEvaluator.evaluate("2 3 4 * +"));
        Assert.assertEquals(21, expressionEvaluator.evaluate("1 2 + 7 *"));
    }

}