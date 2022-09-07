package Ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizens = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        String command = scanner.nextLine();

        while (!command.equals("End")){

            String[] commandSplit = command.split("\\s+");

            String type = commandSplit[0];

            switch (type){
                case "Citizen":
                    String citizenName = commandSplit[1];
                    int age = Integer.parseInt(commandSplit[2]);
                    String citizenId = commandSplit[3];
                    String citizenBirthDate = commandSplit[4];
                    Citizen citizen = new Citizen(citizenName,age,citizenId,citizenBirthDate);
                    citizens.add(citizen);
                    break;
                case "Pet":
                    String petName = commandSplit[1];
                    String petBirthDate = commandSplit[2];
                    Pet pet = new Pet(petName,petBirthDate);
                    pets.add(pet);
                    break;
            }

            command = scanner.nextLine();
        }

        String year = scanner.nextLine();

        for (Citizen citizen : citizens) {
            if(citizen.getBirthDate().contains(year)){
                System.out.println(citizen.getBirthDate());
            }
        }

        for (Pet pet : pets) {
            if(pet.getBirthDate().contains(year)){
                System.out.println(pet.getBirthDate());
            }
        }
    }
}
