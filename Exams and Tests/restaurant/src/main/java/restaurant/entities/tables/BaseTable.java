package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
    }

    private void setSize(int size) {

        if(size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }

        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {

        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }

        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return numberOfPeople * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.allPeople = allPeople();
        isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        if(food.getName().equals("Salad") || food.getName().equals("VeganBiscuits")) {
            healthyFood.add(food);
        }
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        if(beverages.getName().equals("Fresh") || beverages.getName().equals("Smoothie")) {
            this.beverages.add(beverages);
        }
    }

    @Override
    public double bill() {
        double sumFood = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();

        double sumBeverages = beverages.stream().mapToDouble(Beverages::getPrice).sum();

        return sumFood + sumBeverages + allPeople();
    }

    @Override
    public void clear() {
        isReservedTable = false;
        healthyFood.clear();
        beverages.clear();
        this.numberOfPeople = 0;
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {
        return String.format("Table - %d%nSize - %d%nType - %s%nAll price - %.2f",number,size,getClass().getSimpleName(),pricePerPerson);
    }
}
