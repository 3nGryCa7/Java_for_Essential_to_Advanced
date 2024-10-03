/* 作為LinkedQueue 的 Node */
/* Rename: LinkedCard */

package Dealer.src.card;

public class Card {

    private final Suit suit;
    private final Rank rank;
    Card next;

    public enum Suit {
        Spades, Hears, Diamonds, Clubs;
    }

    public enum Rank {
        ONE(1), TWO(2), Three(3), Four(4), Five(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11), TWELVE(12), THIRTEEN(13);

        private final int value;

        Rank(int value){
            this.value = value;
        }
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        next = null;
    }

    @Override
    public String toString() {
        return this.suit.name() + "_" + this.rank.value;
    }
 }