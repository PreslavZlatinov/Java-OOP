package FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] info = scanner.nextLine().split(" ");

            Buyer buyer;
            String name = info[0];
            if(info.length == 3){

                buyer = new Rebel(name);
            } else {
                buyer = new Citizen(name);
            }

            buyers.put(name,buyer);
        }

        String nameToSearch = scanner.nextLine();

        while(!"End".equals(nameToSearch)){

            Buyer buyer = buyers.get(nameToSearch);

            if(buyer != null){
                buyer.buyFood();
            }

            nameToSearch = scanner.nextLine();
        }

        int totalFood= buyers.values().stream().mapToInt(Buyer::getFood).sum();
        System.out.println(totalFood);
    }
}
