package Dealer.src;

import Dealer.src.card.*;
import Dealer.src.define.*;
import Dealer.src.define.Process;
import Dealer.src.methods.*;

public class Main {

    public static void main(String[] args) {
        
        // GenerateCardsTest();
        
        Card[] cards = Methods.GenerateCards();
        // ShuffleTest(cards);
        
        cards = Methods.Shuffle(cards);
        LinkedQueue<Card>[] playerCards = Methods.DealCards(cards);
        
        for (int i=0; i<4; i++) {
            System.out.printf("Player %d's cards: ", i+1);
            playerCards[i].forEach(new Process<Card>() {
                public void todo(Card card) {
                    System.out.print(card.toString() + " ");
                }
            });
            System.out.println();
        }

    }

    public static void GenerateCardsTest() {
        if (Methods.GenerateCards().length != 52) {
            System.out.println("Cards length is wrong");
        }
    }

    public static void ShuffleTest(Card[] cards) {
        Card[] shuffle = Methods.Shuffle(cards);
        for (Card card : shuffle) {
            System.out.print(card.toString() + " ");
        }
    }
}
