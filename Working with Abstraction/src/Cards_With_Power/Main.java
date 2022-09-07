package Cards_With_Power;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RankPower cardRank = RankPower.valueOf(scanner.nextLine());
        SuitPower cardSuit = SuitPower.valueOf(scanner.nextLine());

        Card card = new Card(cardRank,cardSuit);

        System.out.printf("Card name: %s of %s; Card power: %d", card.getCardPower(), card.getCardSuit(), cardRank.getValue()+cardSuit.getValue());
    }
}
