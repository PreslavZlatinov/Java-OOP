package christmasRaces.entities.cars;

public class SportsCar extends BaseCar{

    private int horsePower;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, 3000);
    }
}
