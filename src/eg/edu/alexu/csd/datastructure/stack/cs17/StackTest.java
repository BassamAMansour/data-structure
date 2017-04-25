package eg.edu.alexu.csd.datastructure.stack.cs17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Bassam on 4/20/2017.
 */
public class StackTest {
    Stack stack;
    @Before
    public void setUp() throws Exception {

        stack = new Stack();

    }

    @Test
    public void add() throws Exception {
      stack.add(0, 0);
      stack.add(1,1);
      stack.add(2, 2);
    }

    @Test
    public void pop() throws Exception {
        add();
        int size = stack.size();
        Assert.assertEquals(0, stack.pop());
        Assert.assertEquals(size-1, stack.size());
    }

    @Test
    public void peek() throws Exception {
        stack.push(0);
        stack.push(1);
        stack.push(2);

        Assert.assertEquals(2, stack.peek());
        Assert.assertEquals(2, stack.peek());


        Assert.assertEquals(3, stack.size());
    }

    @Test
    public void push() throws Exception {

        Assert.assertEquals(0, stack.size());

        stack.push(0);
        stack.push(1);
        stack.push(2);

        Assert.assertEquals(3, stack.size());

        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(1, stack.pop());
        Assert.assertEquals(0, stack.pop());

        Assert.assertEquals(0, stack.size());


    }

    @Test
    public void isEmpty() throws Exception {

        Assert.assertTrue(stack.isEmpty());
    }


}