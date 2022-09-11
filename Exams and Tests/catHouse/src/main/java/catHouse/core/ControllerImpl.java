package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {

        House house;

        if("ShortHouse".equals(type)) {
            house = new ShortHouse(name);
        } else if("LongHouse".equals(type)) {
            house = new LongHouse(name);
        } else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        houses.add(house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {

        Toy toy;

        if("Ball".equals(type)) {
            toy = new Ball();
        } else if("Mouse".equals(type)) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        if(toys.findFirst(toyType) == null){
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND,toyType));
        }

        House house = getHouse(houseName);

        house.buyToy(toys.findFirst(toyType));
        toys.removeToy(toys.findFirst(toyType));

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    private House getHouse(String houseName) {
        return houses.stream().filter(s -> s.getName().equals(houseName)).findFirst().orElse(null);
    }


    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        Cat cat;

        if ("ShorthairCat".equals(catType)) {
            cat = new ShorthairCat(catName,catBreed,price);
        } else if("LonghairCat".equals(catType)) {
            cat = new LonghairCat(catName,catBreed,price);
        } else {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House house = getHouse(houseName);

        if (house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("ShorthairCat") ||
                house.getClass().getSimpleName().equals("LongHouse") && catType.equals("LonghairCat")){

            house.addCat(cat);
            return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);

        }

        return String.format(UNSUITABLE_HOUSE);
    }

    @Override
    public String feedingCat(String houseName) {

        House house = getHouse(houseName);

        house.feeding();

        int catCount = house.getCats().size();

        return String.format(FEEDING_CAT,catCount);
    }

    @Override
    public String sumOfAll(String houseName) {

        House house = getHouse(houseName);

        double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();

        double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();

        double pricesSum = catsPrice + toysPrice;

        return String.format(VALUE_HOUSE,houseName,pricesSum);
    }

    @Override
    public String getStatistics() {
        return houses.stream().map(House::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }
}
