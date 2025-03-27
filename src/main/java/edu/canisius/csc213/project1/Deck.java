package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a deck of playing cards with a configurable size.
 */
public class Deck {
    private final List<Card> cards;

    /**
     * Creates a deck with a given size.
     * The size must be a multiple of 4 and at most 52.
     *
     * @param size The number of cards in the deck.
     * @throws IllegalArgumentException if size is invalid.
     */
    public Deck(int size) {
        if (size < 4 || size > 52 || size % 4 != 0) {
            throw new IllegalArgumentException("Invalid deck size: must be a multiple of 4 and no more than 52.");
        }

        this.cards = new ArrayList<>(size);
        int numRanks = size / 4;
        Card.Rank[] allRanks = Card.Rank.values();

        for (int i = 0; i < numRanks; i++) {
            Card.Rank rank = allRanks[allRanks.length - 1 - i]; // Start from ACE, then KING, etc.
            for (Card.Suit suit : Card.Suit.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws the top card from the deck.
     *
     * @return The drawn card.
     * @throws NoSuchElementException if the deck is empty.
     */
    public Card draw() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Deck is empty.");
        }
        return cards.remove(0);
    }

    /**
     * Gets the number of remaining cards in the deck.
     *
     * @return The number of cards left.
     */
    public int size() {
        return cards.size();
    }
}