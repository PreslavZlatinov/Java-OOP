package PizzaCalories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] pizzaInfo = scanner.nextLine().split("\\s+");

        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);

            String[] doughInfo = scanner.nextLine().split("\\s+");

            String flourType = doughInfo[1];
            String bakingTechnique = doughInfo[2];
            int weight = Integer.parseInt(doughInfo[3]);

            Dough dough = new Dough(flourType, bakingTechnique, weight);

            pizza.setDough(dough);

            String topping = scanner.nextLine();

            while (!topping.equals("END")) {

                String[] toppingSplit = topping.split("\\s+");

                String toppingName = toppingSplit[1];
                double toppingWeight = Double.parseDouble(toppingSplit[2]);

                Topping topping1 = new Topping(toppingName, toppingWeight);

                pizza.addTopping(topping1);

                topping = scanner.nextLine();
            }

            System.out.println(pizza.getName() + " - " + pizza.getOverallCalories());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
