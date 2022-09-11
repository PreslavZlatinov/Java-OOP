package garage;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class GarageTests {

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetCars(){
        Garage garage = new Garage();
        Collection<Car> cars = garage.getCars();
        cars.remove(1);
    }

    @Test
    public void test_GetCount(){
        Garage garage = new Garage();
        assertEquals(0,garage.getCount());
        Car car = new Car("Audi A3", 230, 4300.0);
        garage.addCar(car);
        assertEquals(1,garage.getCount());
    }

    @Test
    public void test_FindAllCarsWithMaxSpeedAbove(){
        Garage garage = new Garage();
        Car carOne = new Car("Audi A3", 230, 4300.0);
        Car carTwo = new Car("Audi A4", 240, 3900.0);
        Car carTri = new Car("Audi A6", 270, 6300.0);

        garage.addCar(carOne);
        garage.addCar(carTwo);
        garage.addCar(carTri);

        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(230);

        assertEquals(2,allCarsWithMaxSpeedAbove.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCar_ShouldThrow(){
        Garage garage = new Garage();
        garage.addCar(null);
    }

    @Test
    public void test_GetTheMostExpensiveCar(){
        Garage garage = new Garage();
        Car carOne = new Car("Audi A3", 230, 4300.0);
        Car carTwo = new Car("Audi A4", 240, 3900.0);
        Car carTri = new Car("Audi A6", 270, 6300.0);

        garage.addCar(carOne);
        garage.addCar(carTwo);
        garage.addCar(carTri);

        Car theMostExpensiveCar = garage.getTheMostExpensiveCar();

        assertEquals(carTri.getPrice(),theMostExpensiveCar.getPrice(),0.00);
    }

    @Test
    public void test_FindAllCarsByBrand(){
        Garage garage = new Garage();
        Car carOne = new Car("Audi", 230, 4300.0);
        Car carTwo = new Car("Audi", 240, 3900.0);
        Car carTri = new Car("Audi", 270, 6300.0);

        garage.addCar(carOne);
        garage.addCar(carTwo);
        garage.addCar(carTri);

        List<Car> audi = garage.findAllCarsByBrand("Audi");

        assertEquals(3,audi.size());
    }
}