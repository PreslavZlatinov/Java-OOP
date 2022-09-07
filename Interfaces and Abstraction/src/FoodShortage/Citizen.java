package FoodShortage;

public class Citizen implements Person,Buyer,Identifiable,Birthable{

    private String name;

    private int age;

    private String id;

    private String birthDate;

    private int food;


    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
    }

    public Citizen(String name) {
        this.name = name;
        this.food = 0;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public void buyFood() {
        food += 10;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }
}
