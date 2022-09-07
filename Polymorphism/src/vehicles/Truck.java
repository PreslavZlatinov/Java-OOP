package vehicles;

public class Truck extends AbstractVehicle{

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }

    @Override
    public String drive(double kilometers) {
        return "Truck " + super.drive(kilometers);
    }

    @Override
    public void refuel(double litres) {
        super.refuel(litres*0.95);
    }

    @Override
    public void printFuelQuantity() {
        System.out.print("Truck");
        super.printFuelQuantity();
    }
}
