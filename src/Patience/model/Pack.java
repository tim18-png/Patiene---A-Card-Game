package Patience.model;

import java.util.Collections;

/**
 * Represents a deck of 52 cards
 * Extends CardAction to inherit common card collection functionality
 */
public class Pack extends CardAction {

    /**
     * Constructor - creates a standard 52-card deck
     * Initialises with  combinations of all the suits and values
     */
    public Pack() {
        super();
        String[] suits = {"H", "D", "C", "S"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(suit, value));
            }
        }
    }
    /**
     * FR2: shuffle the order of cards
     * Uses Collections.shuffle to be unbiased when shuffling
     */
    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Cards have been shuffled!");
    }
    /**
     * FR1: Displays and numbers each card
     * Shows total number of cards remaining
     */
    public void showAllCards() {
        System.out.println("Cards in the pack:");
        int count = 1;
        for (Card card : cards) {
            System.out.println(count + ". " + card);
            count++;
        }
        System.out.println("Total cards in the pack: " + cards.size());
    }
}