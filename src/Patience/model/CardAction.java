package Patience.model;

import java.util.ArrayList;

/**
 * Abstract parent class for card actions (Pack and Pile)
 * Provides a common way for managing groups of cards
 * Demonstrates inheritance
 */

public abstract class CardAction {
    protected ArrayList<Card> cards;// Collection of cards managed by this class

    public CardAction() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    /**
     * Removes and returns the top card from the collection
     * @return The top card, or null if collection is empty
     */
    public Card removeTopCard() {
        if (cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public Card getTopCard() {
        if (cards.isEmpty()) return null;
        return cards.get(cards.size() - 1);
    }

    /**
     * Checks if the collection contains no cards
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    /**
     * Returns the number of cards in the collection
     * @return Card count
     */
    public int size() {
        return cards.size();
    }


    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }
}

