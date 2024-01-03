package methods;
import card.Card;
import card.Card.*;
import define.*;

import java.util.Random;
    
public class Methods{

    public static Card[] GenerateCards() {
        Card[] cards = new Card[52];
        int count = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[count] = new Card(suit, rank);
                count++;
            }
        }
        return cards;
    }

    public static Card[] Shuffle(Card[] cards) {
        Random random = new Random();
        for (int i=0; i<52; i++) {
            int x = random.nextInt(52), y = random.nextInt(52);
            Card temp = cards[x];
            cards[x] = cards[y];
            cards[y] = temp;
        }
        return cards;
    }

    // 尚未處理remain cards的問題
    public static LinkedQueue<Card>[] DealCards(Card[] cards) {
        LinkedQueue<Card>[] playerCards = new LinkedQueue[4];

        for (int i=0; i<4; i++) {
            LinkedQueue<Card> playerCard = new LinkedQueue<>();
            for (int j=0; j<13; j++) {
                playerCard.append(cards[i*13+j]);
            }
            playerCards[i] = playerCard;
        }
        return playerCards;
    }

    public static void PrintCards() {}
}