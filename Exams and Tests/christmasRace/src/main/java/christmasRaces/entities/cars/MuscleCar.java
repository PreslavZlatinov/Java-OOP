package christmasRaces.entities.cars;

public class MuscleCar extends BaseCar{

    private int horsePower;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, 5000);
    }
}
