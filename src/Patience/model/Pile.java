package Patience.model;
/**
 * Represents a pile of cards in the game
 * Extends CardAction to inherit common card actions
 */
public class Pile extends CardAction {
    private final String name;// Identifies the pile

    /**
     * Constructor - creates an empty pile with the specified name
     * @param name Identifier for the pile
     */
    public Pile(String name) {
        super();
        this.name = name;
    }

    public String getName() {

        return name;
    }
    /**
     * Decides if piles can be combined
     * based on top cards matching in suit or value
     * @param other The pile to check against
     * @return true if piles can be combined, false otherwise
     */
    public boolean canCombine(Pile other) {
        Card topThis = getTopCard();
        Card topOther = other.getTopCard();
        return topThis != null && topOther != null && topThis.canCombine(topOther);
    }
    /**
     * Combines another pile into this one
     * Adds all cards from the other pile to this one and clears the other pile
     * @param pile The pile to combine with this one
     */
    public void combineWith(Pile pile) {
        cards.addAll(pile.cards);
        pile.cards.clear();
    }
}
