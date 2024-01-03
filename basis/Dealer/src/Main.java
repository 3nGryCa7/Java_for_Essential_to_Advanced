import card.*;
import define.*;
import methods.*;

public class Main {

    public static void main(String[] args) {
        
        // GenerateCardsTest();
        
        Card[] cards = methods.Methods.GenerateCards();
        // ShuffleTest(cards);
        
        cards = methods.Methods.Shuffle(cards);
        LinkedQueue<Card>[] playerCards = methods.Methods.DealCards(cards);
        
        for (int i=0; i<4; i++) {
            System.out.printf("Player %d's cards: ", i+1);
            playerCards[i].forEach(new define.Process<Card>() {
                public void todo(Card card) {
                    System.out.print(card.toString() + " ");
                }
            });
            System.out.println();
        }

    }

    public static void GenerateCardsTest() {
        if (methods.Methods.GenerateCards().length != 52) {
            System.out.println("Cards length is wrong");
        }
    }

    public static void ShuffleTest(Card[] cards) {
        Card[] shuffle = methods.Methods.Shuffle(cards);
        for (Card card : shuffle) {
            System.out.print(card.toString() + " ");
        }
    }
}
