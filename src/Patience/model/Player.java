package Patience.model;

/** Represents a player in the game with name and score
        * Implements Comparable to enable sorting by score
        * Implements Serializable to support file storage
        */
import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
    private String name;  // Player name
    private int score;    // Player score (number of piles at end of game)

    /**
     * Constructor - creates a player record with name and score
     * @param name Player's name
     * @param score Player's score (lower is better)
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the player's name
     * @return Player name
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

         /** Returns the player's score
          * @return Score value (number of piles at end of game)
          */
    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        return name + " Score: " + score;
    }

    /**
     * Compares players names based on score
     * Lower scores (fewer piles) are better
     * @param o The player to compare with
     * @return Negative if this player has a better score and positive otherwise
     */
    public int compareTo(Player o) {
        return Integer.compare(this.score, o.score);
    }
}