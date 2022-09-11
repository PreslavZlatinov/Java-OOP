package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;
import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car{
    private String model;

    private int horsePower;

    private double cubicCentimeters;

    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    private void setModel(String model) {

        if(model == null || model.trim().isEmpty() || model.length() < 4){
            throw new IllegalArgumentException(String.format(INVALID_MODEL,model,4));
        }

        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        if(this.getClass().getSimpleName().equals("SportsCar")){
            if(horsePower > 450 || horsePower < 250){
                throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
            }
        } else if(this.getClass().getSimpleName().equals("MuscleCar")){
            if(horsePower > 600 || horsePower < 400){
                throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
            }
        }

        this.horsePower = horsePower;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsePower * laps;
    }
}
