package farmville;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FarmvilleTests {

    @Test
    public void test_getCount(){
        Farm farm = new Farm("Farm",12);
        Animal animalOne = new Animal("Cow",10.0);
        Animal animalTwo = new Animal("Bull",11.0);

        farm.add(animalOne);
        farm.add(animalTwo);

        assertEquals(2,farm.getCount());
    }

    @Test
    public void test_getName(){
        Farm farm = new Farm("Farm",12);

        assertEquals("Farm",farm.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal(){
        Farm farm = new Farm("Farm",1);
        Animal animalOne = new Animal("Cow",10.0);
        Animal animalTwo = new Animal("Bull",11.0);

        farm.add(animalOne);
        farm.add(animalTwo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal2(){
        Farm farm = new Farm("Farm",2);
        Animal animalOne = new Animal("Cow",10.0);

        farm.add(animalOne);
        farm.add(animalOne);
    }

    @Test
    public void test_Remove(){
        Farm farm = new Farm("Farm",12);
        Animal animalOne = new Animal("Cow",10.0);
        Animal animalTwo = new Animal("Bull",11.0);

        farm.add(animalOne);
        farm.add(animalTwo);

        boolean cow = farm.remove("Cow");

        assertTrue(cow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacity(){
        Farm farm = new Farm("Farm",-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName(){
        Farm farm = new Farm(null,1);
    }
}
