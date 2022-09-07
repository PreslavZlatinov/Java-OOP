package vehicles;

public class Bus extends AbstractVehicle{
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public String drive(double kilometers) {
        return "Bus " + super.drive(kilometers);
    }

    @Override
    public void printFuelQuantity() {
        System.out.print("Bus");
        super.printFuelQuantity();
    }
}
