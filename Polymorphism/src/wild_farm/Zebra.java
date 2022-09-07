package wild_farm;

public class Zebra extends Mammal{

    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if(food instanceof Meat){
            throw new IllegalArgumentException(this.getClass().getSimpleName() +"s are not eating that type of food!");

        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
