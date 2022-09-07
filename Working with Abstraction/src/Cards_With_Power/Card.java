package Cards_With_Power;

public class Card {
   private RankPower cardPower;
   private SuitPower cardSuit;

   public Card(RankPower cardPower, SuitPower cardSuit) {
      this.cardPower = cardPower;
      this.cardSuit = cardSuit;
   }

   public RankPower getCardPower() {
      return cardPower;
   }

   public SuitPower getCardSuit() {
      return cardSuit;
   }
}
