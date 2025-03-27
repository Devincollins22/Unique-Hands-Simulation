package edu.canisius.csc213.project1;

import java.util.Objects;

/**
 * Represents a playing card with a suit and rank.
 */
public class Card {

    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }

    public enum Rank { 
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, 
        JACK, QUEEN, KING, ACE 
    }

    private final Suit suit;
    private final Rank rank;

    /**
     * Constructor to initialize a card's suit and rank.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns the suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the string representation in "RANK of SUIT" format.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Checks equality based on suit and rank.
     */
    @Override
    public boolean equals(Object obj) {
        // Checks if the object is the same instance or if the class matches
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Cast to Card to compare fields
        Card card = (Card) obj;
        return suit == card.suit && rank == card.rank;
    }

    /**
     * Returns the hash code based on suit and rank.
     */
    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);  // Combines the hash codes of both fields
    }
}