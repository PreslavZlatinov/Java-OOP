package Card_Rank;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        Arrays.stream(Rank.values()).forEach(rank -> System.out.printf("Ordinal value: %d; Name value: %s%n",rank.ordinal(),rank.name()));
    }
}
