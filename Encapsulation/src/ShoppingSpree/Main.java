package ShoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> peopleList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        List<String> people = Arrays.stream(scanner.nextLine().split(";")).collect(Collectors.toList());
        List<String> products = Arrays.stream(scanner.nextLine().split(";")).collect(Collectors.toList());


        try {

            for (String person : people) {
                List<String> info = Arrays.stream(person.split("=")).collect(Collectors.toList());
                String name = info.get(0);
                double money = Double.parseDouble(info.get(1));
                Person person1 = new Person(name, money);
                peopleList.add(person1);
            }

            for (String product : products) {
                List<String> info = Arrays.stream(product.split("=")).collect(Collectors.toList());
                String productName = info.get(0);
                double cost = Double.parseDouble(info.get(1));
                Product product1 = new Product(productName, cost);
                productList.add(product1);
            }

            String command = scanner.nextLine();

            while (!command.equals("END")) {

                String[] commandSplit = command.split("\\s+");
                String name = commandSplit[0];
                String product = commandSplit[1];

                Product productToBuy = productList.stream()
                        .filter(p -> p.getName().equals(product))
                        .findFirst().orElse(null);

                peopleList.stream()
                        .filter(person -> person.getName().equals(name))
                        .findFirst()
                        .get()
                        .buyProduct(productToBuy);

                command = scanner.nextLine();
            }

            for (Person person : peopleList) {
                System.out.println(person);
            }

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
