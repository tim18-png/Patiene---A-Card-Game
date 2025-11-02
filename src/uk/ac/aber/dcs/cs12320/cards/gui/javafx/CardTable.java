package uk.ac.aber.dcs.cs12320.cards.gui.javafx;

/**
 * Displays the card images on a table (the Javafx stage)
 * @author Titus Mathew
 * @version 1.0
 */

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CardTable {
    private final Stage stage;
    private String[] cards;
    private boolean done;

    private static final String TITLE = "Patience Patience.Patience.model.model.Card Patience.Patience.model.model.Main.Game";
    private static final String VERSION = "1.0";

    /**
     * Constructor for the uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable
     * @param stage the JavaFX stage to display cards on
     */
    public CardTable(Stage stage) {
        this.stage = stage;
        stage.setTitle(TITLE + " " + VERSION);
    }

    /**
     * Called when the user quits the game. It results
     * in the face-down pack of cards not being displayed.
     */
    public void allDone() {
        done = true;
    }

    /**
     * Displays all the face-up cards (just the top showing cards)
     * and if the game is not over then also displays the face-down deck.
     * @param c the list of face-up cards
     */
    public void cardDisplay(ArrayList<String> c) {

        // We need to do this within the GUI thread. We assume
        // that the method is called by a non-GUI thread
        Platform.runLater(() -> {
            cards = new String[c.size()];
            cards = c.toArray(cards);

            ScrollPane sp = new ScrollPane();
            Scene scene = new Scene(sp, 800, 155);
            scene.setFill(Color.BLACK);
            HBox box = new HBox();
            Image image = null;

            if (cards != null) {
                for (String card : cards) {
                    String file = "cards/" + card;
                    image = new Image(getClass().getClassLoader().getResource(file).toString(), true);
                    drawCards(box, image);
                }
            }

            if (!done) {
                // Draws the face-down top card of our pack of cards
                String file = "cards/b.gif";
                image = new Image(getClass().getClassLoader().getResource(file).toString(), true);
                drawCards(box, image);
            }

            sp.setContent(box);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            stage.setScene(scene);
            stage.show();
        });

    }

    private void drawCards(HBox box, Image image) {
        ImageView iv;
        iv = new ImageView();
        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        iv.setImage(image);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);

        box.getChildren().add(iv);
    }


}
