package com.teamnet;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Mihaela.Stoian on 7/10/2017.
 */
public class MyUnitTest {

    @Test
    public void testConcatenate() {
        MyClass myUnit = new MyClass();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    @Test
    public void testConcatenateNulls() {
        MyClass myClass = new MyClass();
        String result = myClass.concatenate(null, null);
        assertEquals(null, result);

        result = myClass.concatenate("one", null);
        assertEquals("one", result);

        result = myClass.concatenate(null, "two");
        assertEquals("two", result);
    }

    @Test
    public void testGetBoolean() {
        MyClass myClass = new MyClass();
//        assertFalse(myClass.getBoolean());
        assertThat(1 + 2, is(5));
    }
}