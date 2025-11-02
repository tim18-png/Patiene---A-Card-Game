package Patience.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Manages game scores and player records
 * Uses Text-based database for scores
 */
public class ScoreBoard {
    private ArrayList<Player> players;  // Collection of player records
    private Scanner scanner;            // Input reader for player names
    private final String SCORE_FILE = "scoreBoard.txt";  // Filename for storing scores


    public ScoreBoard() {
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadScores();

    }

    /**
     * Loads saved scores from the text file
     * Creates the file if it doesn't exist
     */
    private void loadScores() {
        File file = new File(SCORE_FILE);
        if (!file.exists()) {
            System.out.println("No existing score file found. A new one will be created when needed.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip header line if it exists
            line = reader.readLine();
            if (line != null && line.contains("Format")) {
                // This was a header, skip it
            } else if (line != null) {
                // Not a header, process this line
                processScoreLine(line);
            }

            // Read the rest of the file
            while ((line = reader.readLine()) != null) {
                processScoreLine(line);
            }

            // Sort players after loading (lowest pile count first)
            if (!players.isEmpty()) {
                Collections.sort(players);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Could not find score file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading score file: " + e.getMessage());
        }
    }

    /**
     * Process a single line from the score file
     */
    private void processScoreLine(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String name = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                players.add(new Player(name, score));
            }
        } catch (NumberFormatException e) {
            // Skip invalid lines without error message
        }
    }
    /**
     * Records a new game score
     * Asks for player name and adds to the scoreboard
     * @param pileCount Final number of piles (lower is better)
     */
    public void addScore(int pileCount) {
        System.out.println("=====GAME OVER=====");
        System.out.println("Your score (piles remaining): " + pileCount);

        // The aim is to reduce piles to 1, so lower is better
        if (pileCount == 1) {
            System.out.println("SUCCESSFUL GAME! You reached the minimum possible piles!");
        } else if (pileCount > 1) {
            System.out.println("Score Saved");
        }

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();

        // Handle empty name
        if (playerName.isEmpty()) {
            playerName = "Anonymous";
        }

        // Create new player and add to list
        Player newPlayer = new Player(playerName, pileCount);
        players.add(newPlayer);

        // Sort players (lowest pile count first)
        Collections.sort(players);

        // Save scores with retry logic
        try {
            saveScores();
            System.out.println("Score recorded for " + playerName + ": " + pileCount + " piles");
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Save all scores to the text file
     *
     * @throws IOException if there's an error writing to the file
     */
    private void saveScores() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE))) {
            // Write a header line
            writer.println(" Patience Scores - Format: Name,PileCount");

            // Write each player's data as name,score
            for (Player player : players) {
                writer.println(player.getName() + "," + player.getScore());


            }
        }
        System.out.println("Scores saved to " + SCORE_FILE);
    }

    /**
     * Display the top scores (lowest pile count is best)
     */
    public void showTopTen() {
        if (players.isEmpty()) {
            System.out.println("No scores saved yet!");
            return;
        }

        System.out.println("\n==== TOP 10 SCORES ====");
        System.out.println("(Lower scores are better)");

        int limit = Math.min(10, players.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }
    }

}