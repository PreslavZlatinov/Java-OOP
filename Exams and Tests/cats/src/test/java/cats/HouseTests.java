package cats;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTests {


    @Test
    public void test_GetName(){
        House house = new House("Nikola",69);
        String name = house.getName();
        assertEquals("Nikola",name);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName(){
        House house = new House(null,69);
    }

    @Test
    public void test_GetCapacity(){
        House house = new House("Nikola",69);
        assertEquals(69,house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacity(){
        House house = new House("Nikola",-1);
    }

    @Test
    public void test_GetCount(){

        House house = new House("Nikola",69);

        Cat catOne = new Cat("Kolio");
        Cat catTwo = new Cat("Lea");
        Cat catThree = new Cat("Stoqn");
        Cat catFour = new Cat("Atanas");

        house.addCat(catOne);
        house.addCat(catTwo);
        house.addCat(catThree);
        house.addCat(catFour);

        assertEquals(4,house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCat(){
        House house = new House("Nikola",1);

        Cat catOne = new Cat("Kolio");
        Cat catTwo = new Cat("Lea");

        house.addCat(catOne);
        house.addCat(catTwo);
    }

    @Test
    public void test_RemoveCat(){
        House house = new House("Nikola",2);

        Cat catOne = new Cat("Kolio");
        Cat catTwo = new Cat("Lea");

        house.addCat(catOne);
        house.addCat(catTwo);

        house.removeCat(catOne.getName());

        assertEquals(1,house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveCat_ShouldThrow(){
        House house = new House("Nikola",2);

        Cat catOne = new Cat("Kolio");
        Cat catTwo = new Cat("Lea");

        house.addCat(catOne);
        house.addCat(catTwo);

        house.removeCat("Nikola");

    }

    @Test
    public void test_CatForSale(){
        House house = new House("Nikola",2);

        Cat catOne = new Cat("Kolio");
        Cat catTwo = new Cat("Lea");

        house.addCat(catOne);
        house.addCat(catTwo);

        Cat cat = house.catForSale(catOne.getName());

        assertEquals(catOne.getName(),cat.getName());
        assertFalse(catOne.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CatForSale_Exception(){
        House house = new House("Nikola",2);

        Cat cat = house.catForSale(null);
    }

    @Test
    public void test_Statistics(){
        House house = new House("Nikola",2);

        Cat catOne = new Cat("Kolio");

        house.addCat(catOne);

        String catInfo = "The cat Kolio is in the house Nikola!";

        String statistics = house.statistics();

        assertEquals(catInfo,statistics);
    }
}
