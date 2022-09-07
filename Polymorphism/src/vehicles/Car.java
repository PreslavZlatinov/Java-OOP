package vehicles;

public class Car extends AbstractVehicle{

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }

    @Override
    public String drive(double kilometers) {
        return "Car " + super.drive(kilometers);
    }

    @Override
    public void printFuelQuantity() {
        System.out.print("Car");
        super.printFuelQuantity();
    }
}
