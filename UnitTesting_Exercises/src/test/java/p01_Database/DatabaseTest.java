package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;

    private Integer[] integers;

    @Before
    public void setUp() throws OperationNotSupportedException {
        integers = new Integer[]{1,2,3,4,5};
        database = new Database(integers);
    }

    @Test
    public void test_CreateConstructor_ShouldCreate_Constructor() throws OperationNotSupportedException {

        Assert.assertEquals(integers.length,database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateConstructor_ShouldThrow_Exception() throws OperationNotSupportedException {
        Integer[] integers = new Integer[17];

        Database database = new Database(integers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_ShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_Add_ShouldAdd_Elements() throws OperationNotSupportedException {

        int arrayLength = integers.length;

        database.add(7);

        Assert.assertEquals(arrayLength+1,database.getElements().length);
    }

    @Test
    public void test_Remove_ShouldRemove_Elements() throws OperationNotSupportedException {

        database.remove();

        Assert.assertEquals(4,database.getElements().length);

        database.remove();

        Assert.assertEquals(3,database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow() throws OperationNotSupportedException {
        Integer[] integers1 = new Integer[1];

        Database database1 = new Database(integers1);

        database1.remove();
        database1.remove();
    }
}