package vehicles;

public interface Vehicle {
    String drive(double kilometers);
    void refuel(double litres);

    void printFuelQuantity();
}
