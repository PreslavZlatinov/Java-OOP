package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {

        Area area;

        if("WaterArea".equals(areaType)) {
            area = new WaterArea(areaName);
        } else if ("LandArea".equals(areaType)) {
            area = new LandArea(areaName);
        } else {
            throw new NullPointerException(INVALID_AREA_TYPE);
        }

        areas.add(area);

        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {

        Food food;

        if("Vegetable".equals(foodType)) {
            food = new Vegetable();
        } else if ("Meat".equals(foodType)) {
            food = new Meat();
        } else {
            throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }

        foodRepository.add(food);

        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE,foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {

        Food food = foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        Area currentArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);

        currentArea.addFood(food);

        foodRepository.remove(food);

        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

        Animal animal;

        if("AquaticAnimal".equals(animalType)) {
            animal = new AquaticAnimal(animalName,kind,price);
        } else if ("TerrestrialAnimal".equals(animalType)) {
            animal = new TerrestrialAnimal(animalName,kind,price);
        } else {
            throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }

        Area currentArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);

        if ((animal instanceof AquaticAnimal && currentArea instanceof LandArea) ||
                (animal instanceof TerrestrialAnimal && currentArea instanceof WaterArea)) {
            return AREA_NOT_SUITABLE;
        }

        currentArea.addAnimal(animal);

        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {

        Area currentArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);

        currentArea.feed();

        return String.format(ANIMALS_FED, currentArea.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {

        Area currentArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);

        double sum = currentArea.getAnimals().stream().mapToDouble(Animal::getKg).sum();

        return String.format(KILOGRAMS_AREA, areaName, sum);
    }

    @Override
    public String getStatistics() {
        return areas.stream().map(Area::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
