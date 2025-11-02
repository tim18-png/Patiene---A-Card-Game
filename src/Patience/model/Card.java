package Patience.model;
/**
 * Represents a playing card in the patience card game.
 * Each card has a suit (Hearts, Diamonds, Clubs, or Spades) and a value (2-10, J, Q, K, A).
 */
public class Card {
    private final String suit; // Stores the cards suit e.g. Hearts or Club
    private final String value; // Stores the cards face value e.g. 5 or Ace

    /**
     * Constructor - creates a new card with suit and value
     * @param suit Card suit (H, D, C, S)
     * @param value Card value (2-10, J, Q, K, A)
     */
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    /**
     * Returns the card's suit
     * @return Suit code (H, D, C, S)
     */
    public String getSuit() {
        return suit;
    }
    /**
     * Returns the card's value
     * @return Value code (2-10, J, Q, K, A)
     */
    public String getValue() {
        return value;
    }
    /**
     * Decides if cards can combine
     * based on matching suit or value
     * @param other The card to check against
     * @return true if the cards can be combined, false otherwise
     */
    public boolean canCombine(Card other) { // used to combine pile when same suit or value is produced
        return this.suit.equals(other.suit) || this.value.equals(other.value);
    }

    @Override
    public String toString() { // Enables the card to be printed as a sentence in CLI
        String suitName = getSuitName(suit);
        return getValueName(value) + " of " + suitName;
    }

    private String getValueName(String value) {
        switch (value) {
            case "J":
                return "Jack";
            case "Q":
                return "Queen";
            case "K":
                return "King";
            case "A":
                return "Ace";
            default:
                return value;

        }
    }

    private String getSuitName(String suit) {
        switch (suit) {
            case "H":
                return "Hearts";
            case "D":
                return "Diamonds";
            case "C":
                return "Clubs";
            case "S":
                return "Spades";
            default:
                return suit;
        }
    }
}

