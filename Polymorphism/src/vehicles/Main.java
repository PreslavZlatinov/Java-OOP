package vehicles;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");

        double carFuelQuantity = Double.parseDouble(carInfo[1]);
        double carFuelConsumption = Double.parseDouble(carInfo[2]);
        double carTankCapacity = Double.parseDouble(carInfo[3]);

        Car car = new Car(carFuelQuantity,carFuelConsumption, carTankCapacity);

        String[] truckInfo = scanner.nextLine().split("\\s+");

        double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
        double truckFuelConsumption = Double.parseDouble(truckInfo[2]);
        double truckTankCapacity = Double.parseDouble(truckInfo[3]);

        Truck truck = new Truck(truckFuelQuantity,truckFuelConsumption,truckTankCapacity);

        String[] busInfo = scanner.nextLine().split("\\s+");

        double busFuelQuantity = Double.parseDouble(busInfo[1]);
        double busFuelConsumption = Double.parseDouble(busInfo[2]);
        double busTankCapacity = Double.parseDouble(busInfo[3]);

        Bus bus = new Bus(busFuelQuantity,busFuelConsumption,busTankCapacity);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            String commandType = command[0];

            String vehicleType = command[1];

            if("Drive".equals(commandType)) {

                if("Car".equals(vehicleType)){
                    System.out.println(car.drive(Double.parseDouble(command[2])));
                } else if("Truck".equals(vehicleType)) {
                    System.out.println(truck.drive(Double.parseDouble(command[2])));
                } else if("Bus".equals(vehicleType)) {
                    bus.setFuelConsumption(bus.getFuelConsumption() + 1.4);
                    System.out.println(bus.drive(Double.parseDouble(command[2])));
                    bus.setFuelConsumption(bus.getFuelConsumption() - 1.4);
                }

            } else if("Refuel".equals(commandType)) {
                if("Car".equals(vehicleType)){
                    car.refuel(Double.parseDouble(command[2]));
                } else if("Truck".equals(vehicleType)) {
                    truck.refuel(Double.parseDouble(command[2]));
                } else if("Bus".equals(vehicleType)) {
                    bus.refuel(Double.parseDouble(command[2]));
                }
            } else if("DriveEmpty".equals(commandType)) {
                System.out.println(bus.drive(Double.parseDouble(command[2])));
            }
        }


        car.printFuelQuantity();
        truck.printFuelQuantity();
        bus.printFuelQuantity();
    }
}
