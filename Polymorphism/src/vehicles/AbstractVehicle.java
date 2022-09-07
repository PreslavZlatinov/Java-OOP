package vehicles;

import java.text.DecimalFormat;

public abstract class AbstractVehicle implements Vehicle{

    private double fuelQuantity;
    private double fuelConsumption;

    private double tankCapacity;

    private DecimalFormat df = new DecimalFormat("#.##");

    public AbstractVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }


    @Override
    public void refuel(double litres) {
        if(litres <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if(litres > this.tankCapacity - this.fuelQuantity){
            System.out.println("Cannot fit fuel in tank");
            return;
        }

        setFuelQuantity(getFuelQuantity() + litres);
    }

    @Override
    public String drive(double kilometers) {
        double totalFuelConsumption = kilometers * getFuelConsumption();

        if(totalFuelConsumption > getFuelQuantity()){
            return "needs refueling";
        }

        setFuelQuantity(getFuelQuantity()-totalFuelConsumption);

        return "travelled " + df.format(kilometers) + " km";
    }

    @Override
    public void printFuelQuantity(){
        System.out.printf(": %.2f%n",fuelQuantity);
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
