package Patience.model;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main application class for the CLI version Patience game
 * Manages game state and user interactions through a text-based interface
 */

public class Application {
    private Pack pack;  // Deck of cards for the game
    private ArrayList<Pile> piles; // Collection of card piles in use
    private ScoreBoard scoreBoard; // Records player scores

    public Application() {
        pack = new Pack();
        piles = new ArrayList<>(); // Initialise the ArrayList
        scoreBoard = new ScoreBoard(); // Initialise the ScoreBoard
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        pack = new Pack(); // Initialise new card pack

        boolean exit = false;
        while (!exit) {
            // CLI menu for user
            System.out.println("\n=====PATIENCE CARD GAME MENU=====");
            System.out.println("1. Show the full pack");
            System.out.println("2. Shuffle the pack");
            System.out.println("3. Deal a card");
            System.out.println("4. Make a move, move last pile onto previous one");
            System.out.println("5. Make a move, move last pile back over two piles");
            System.out.println("6. Amalgamate piles in the middle (by giving their numbers)");
            System.out.println("7. Show all cards in play");
            System.out.println("8. Play for me once");
            System.out.println("9. Play for me a number of times");
            System.out.println("10. Show top ten results");
            System.out.println("Q. Quit");

            // Input processing
            System.out.print("Enter option: ");
            String choice = scanner.nextLine().toUpperCase(); // Reads input and makes it uppercase if lower case is entered

            /* controls CLI menu option */
            switch (choice) {
                case "1": // FR1: Show the full pack
                    showFullPack();
                    break;
                case "2": // FR2: Shuffle the pack
                    pack.shuffle();
                    break;
                case "3": // FR3: Deal a card
                    dealCard();
                    break;
                case "4": // FR4: Move last pile onto previous one
                    moveBackOne();
                    break;
                case "5": // FR5: Move last pile back over two piles
                    moveBackTwo();
                    break;
                case "6": // FR6: Amalgamate piles in the middle
                    amalgamatePiles(scanner);
                    break;
                case "7": // FR7: Show all cards in play
                    cardsInPlay();
                    break;
                case "8": // FR8: Play for me once
                    playOnce();
                    break;
                case "9": // FR9: Play for me a number of times
                    playMultiple(scanner);
                    break;
                case "10": // FR10: Show top ten results
                    topTen();
                    break;
                case "Q": // FR11: Quit
                    // Only save score if game has started (piles exist)
                    if (!piles.isEmpty()) {
                        // Call endGame when quitting if there are piles
                        endGame();
                    }
                    exit = true;
                    System.out.println("Game Over!");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }


    // FR1: Show the full pack
    private void showFullPack() {
        pack.showAllCards();
    }

    // FR3: Deal a card
    private void dealCard() {
        Card card = pack.removeTopCard();
        if (card == null) {
            System.out.println("No remaining cards to deal!");
            return;
        }
        // Create a new pile with a sequential identifier
        Pile newPile = new Pile("Pile " + (piles.size() + 1));
        newPile.addCard(card);
        piles.add(newPile);
        System.out.println("Dealt: " + card);
        System.out.println("Created new pile: " + newPile.getName());
    }
    /**
     * FR4: Attempts to combine the last pile with the adjacent previous pile
     * Checks game for valid moves (matching suit or value)
     */
    private void moveBackOne() {
        if (piles.size() < 2) {
            System.out.println("Need at least two piles to perform this move!");
            return;
        }

        Pile lastPile = piles.get(piles.size() - 1);
        Pile previousPile = piles.get(piles.size() - 2);

        // Attempt to combine piles a valid option
        if (previousPile.canCombine(lastPile)) {
            previousPile.combineWith(lastPile);
            piles.remove(piles.size() - 1);
            System.out.println("Successfully moved last pile onto previous one");
        } else {
            System.out.println("Can't combine these piles - no matching suit or value!");
        }
    }

    // FR5: Move last pile back over two piles
    private void moveBackTwo() {
        if (piles.size() < 3) {
            System.out.println("Need at least three piles to perform this move!");
            return;
        }

        Pile lastPile = piles.get(piles.size() - 1);
        Pile targetPile = piles.get(piles.size() - 3);
        // Check if piles can be combined according to game
        if (targetPile.canCombine(lastPile)) {
            targetPile.combineWith(lastPile);
            piles.remove(piles.size() - 1);
            System.out.println("Successfully moved last pile back over two piles.");
        } else {
            System.out.println("Cannot combine these piles - no matching suit or value!");
        }
    }
     /** Enables middle-pile combine
     * @param scanner Input reader for user selections
     */
    private void amalgamatePiles(Scanner scanner) {
        if (piles.size() < 2) {
            System.out.println("Need at least two piles to amalgamate!");
            return;
        }
        // Get user input for pile selection
        System.out.print("Enter first pile number (1-" + piles.size() + "): ");
        int firstPileIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Enter second pile number (1-" + piles.size() + "): ");
        int secondPileIndex = Integer.parseInt(scanner.nextLine()) - 1;
        // Attempt to combine if valid option
        if (firstPileIndex < 0 || firstPileIndex >= piles.size() ||
                secondPileIndex < 0 || secondPileIndex >= piles.size() ||
                firstPileIndex == secondPileIndex) {
            System.out.println("Invalid pile numbers!");
            return;
        }

        Pile firstPile = piles.get(firstPileIndex);
        Pile secondPile = piles.get(secondPileIndex);

        if (firstPile.canCombine(secondPile)) {
            firstPile.combineWith(secondPile);
            piles.remove(secondPileIndex);
            System.out.println("Successfully amalgamated piles.");
        } else {
            System.out.println("Cannot combine these piles - no matching suit or value!");
        }
    }

    /**
     * FR7: Displays all piles currently in play
     * Shows the top card of each pile
     */
    private void cardsInPlay() {
        if (piles.isEmpty()) {
            System.out.println("No cards in play yet!");
            return;
        }

        System.out.println("Cards currently in play:");
        for (int i = 0; i < piles.size(); i++) {
            Pile currentPile = piles.get(i);
            System.out.print(" Pile " + (i + 1) + " top card: " + currentPile.getTopCard());
            System.out.println();

        }
    }

    // FR8: Play for me once
    private void playOnce() {
        if (piles.size() < 2) {
            System.out.println("Need at least two piles to make a move!");
            return;
        }

        // First try moving the last pile over two piles (if possible)
        if (piles.size() >= 3) {
            Pile lastPile = piles.get(piles.size() - 1);
            Pile targetPile = piles.get(piles.size() - 3);

            if (targetPile.canCombine(lastPile)) {
                targetPile.combineWith(lastPile);
                piles.remove(piles.size() - 1);
                System.out.println("Auto-played: Moved last pile back over two piles.");
                return;
            }
        }

        // If that's not possible, try moving to the adjacent pile
        Pile lastPile = piles.get(piles.size() - 1);
        Pile previousPile = piles.get(piles.size() - 2);

        if (previousPile.canCombine(lastPile)) {
            previousPile.combineWith(lastPile);
            piles.remove(piles.size() - 1);
            System.out.println("Auto-played: Moved last pile onto previous one.");
            return;
        }

        // Check all other possible combinations (middle piles)
        for (int i = 0; i < piles.size() - 1; i++) {
            for (int j = 0; j < piles.size(); j++) {
                if (i != j && piles.get(i).canCombine(piles.get(j))) {
                    System.out.println("Auto-played: Amalgamated pile " + (i + 1) + " with pile " + (j + 1));
                    piles.get(i).combineWith(piles.get(j));
                    piles.remove(j);
                    return;
                }
            }
        }

        // If no moves are possible, deal a card if there are cards left
        if (!pack.isEmpty()) {
            dealCard();
            System.out.println("Auto-played: No valid moves, dealt a new card.");
        } else {
            System.out.println("No valid moves and no cards left in the pack!");
            int finalScore = piles.size();
            System.out.println("Game over! Final score: " + finalScore + " piles");
            scoreBoard.addScore(finalScore);
        }
    }

    /**
     * FR9: Makes multiple auto moves
     * Continues until specified move count
     * @param scanner Input reader for number of moves selected
     */
    private void playMultiple(Scanner scanner) {
        System.out.print("How many moves would you like to make? ");
        int moves = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < moves; i++) {
            System.out.println("Move " + (i + 1) + ":");
            playOnce();

            // Check if game is over (no cards left and no valid moves)
            if (pack.isEmpty()) {
                boolean movePossible = false;
                for (int j = 0; j < piles.size() - 1; j++) {
                    for (int k = j + 1; k < piles.size(); k++) {
                        if (piles.get(j).canCombine(piles.get(k))) {
                            movePossible = true;
                            break;
                        }
                    }
                    if (movePossible) break;
                }

                if (!movePossible) {
                    System.out.println("No more valid moves possible.");
                    break;
                }
            }
        }
    }

    /**
     * FR10: Displays the top ten player scores
     * Shows sorted results with best (lowest) scores first
     */
    private void topTen() {
        scoreBoard.showTopTen();
    }

    private void endGame() {
        int finalPileCount = piles.size();

        if (finalPileCount == 1) {
            System.out.println("You Win! Game completed with just 1 pile!");
        } else {
            System.out.println("Game ended with " + finalPileCount + " piles remaining.");
        }

        // Record the player's score
        scoreBoard.addScore(finalPileCount);
    }

    public static void main(String[] args) {
        new Application().startGame();
    }
}


