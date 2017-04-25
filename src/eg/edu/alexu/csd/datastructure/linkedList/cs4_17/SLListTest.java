package eg.edu.alexu.csd.datastructure.linkedList.cs4_17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Bassam on 4/9/2017.
 */
public class SLListTest {

    SLList list = new SLList();


    @Before
    public void setUp() throws Exception {
        list.add(null);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(null);
    }

    @Test
    public void add() throws Exception {
        Assert.assertNull("Not null", list.get(0));
        Assert.assertNull("Not null", list.get(7));
        list.add(4, null);
        Assert.assertNull(null, list.get(4));
    }

    @Test
    public void add1() throws Exception {
        list.add(8);
        Assert.assertEquals(8, list.get(list.size() - 1));
    }

    @Test
    public void set() throws Exception {


        list.set(6, 5);
        Assert.assertEquals(5, list.get(6));


    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(list.isEmpty());
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void remove() throws Exception {
        list.remove(6);
        Assert.assertFalse(list.contains(6));
    }

    @Test
    public void sublist() throws Exception {
        SLList generatedSubList = new SLList();
        generatedSubList.add(1);
        generatedSubList.add(2);
        generatedSubList.add(3);

        SLList subList = (SLList) list.sublist(1, 3);

        for (int i = 0; i < generatedSubList.size() - 1; i++) {
            Assert.assertEquals(generatedSubList.get(i), subList.get(i));
        }
    }

    @Test
    public void contains() throws Exception {

        Assert.assertTrue("True", list.contains(3));
        Assert.assertFalse("False", list.contains(10));
    }

}