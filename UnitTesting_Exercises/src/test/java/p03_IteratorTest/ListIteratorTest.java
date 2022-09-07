package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateConstructor_ShouldThrow_Exception_IfNullIsPassed() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(null);
    }

    @Test
    public void test_CreateConstructor_ShouldCreate_Constructor() throws OperationNotSupportedException {
        String[] elements = new String[]{"Preso","Rik","Acho","Dal"};
        ListIterator listIterator = new ListIterator(elements);
    }

    @Test(expected = IllegalStateException.class)
    public void test_Print_ShouldThrow_Exception() throws OperationNotSupportedException {
        String[] strings = new String[0];
        ListIterator listIterator = new ListIterator(strings);
        listIterator.print();
    }

    @Test
    public void test_Print_ShouldReturn_ElementAtTheCurrentIndex() throws OperationNotSupportedException {
        String[] strings = new String[]{"Pesho","Kiro","Miro"};
        ListIterator listIterator = new ListIterator(strings);

        String elementToCheck = listIterator.print();
        Assert.assertEquals(strings[0],elementToCheck);
    }

    @Test
    public void test_Move_ShouldReturn_True() throws OperationNotSupportedException {
        String[] strings = new String[]{"Pesho","Kiro","Miro"};
        ListIterator listIterator = new ListIterator(strings);

       boolean isMoved = listIterator.move();

        assertTrue(isMoved);
    }

    @Test
    public void test_Move_ShouldReturn_False() throws OperationNotSupportedException {
        String[] strings = new String[0];
        ListIterator listIterator = new ListIterator(strings);

        boolean isMoved = listIterator.move();

        assertFalse(isMoved);
    }
}