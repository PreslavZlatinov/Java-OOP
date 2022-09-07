package wild_farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalInfo = scanner.nextLine();

        List<Animal> animalList = new ArrayList<>();


        while (!"End".equals(animalInfo)){

            String foodToEat = scanner.nextLine();

            Animal animal = createAnimal(animalInfo);

            Food food = createFood(foodToEat);

            animal.makeSound();

            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            animalList.add(animal);

            animalInfo = scanner.nextLine();
        }

        animalList.forEach(System.out::println);
    }

    private static Food createFood(String foodToEat) {
        String[] foodTokens = foodToEat.split("\\s+");

        String foodKind = foodTokens[0];
        Integer quantity = Integer.parseInt(foodTokens[1]);

        if("Vegetable".equals(foodKind)){
            return new Vegetable(quantity);
        }
        return new Meat(quantity);
    }

    private static Animal createAnimal(String animalInfo) {
        String[] animalInfoTokens = animalInfo.split("\\s+");

        String animal = animalInfoTokens[0];
        String name = animalInfoTokens[1];
        Double weight = Double.parseDouble(animalInfoTokens[2]);
        String region = animalInfoTokens[3];

        switch (animal){
            case "Cat":
                String breed = animalInfoTokens[4];
                return new Cat(name, animal, weight, region, breed);
            case "Tiger":
                return new Tiger(name, animal, weight, region);
            case "Mouse":
                return new Mouse(name, animal, weight, region);
            case "Zebra":
                return new Zebra(name, animal, weight, region);
            default:
                throw new IllegalArgumentException("No such animal");
        }
    }
}
