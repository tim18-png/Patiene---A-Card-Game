# 🃏 Patience Card Game (Java CLI & GUI Model)

This is a **Java implementation** of a Patience card game, a solitaire variant where the objective is to strategically combine cards to reduce the total number of piles to one. The application features a comprehensive **Command-Line Interface (CLI)** for all gameplay actions and is structured to support a separate **JavaFX Graphical User Interface (GUI)**. Player scores are tracked and saved in a text-based database.

---

## ✨ Features and Functional Requirements

The project successfully implements all functional requirements (FR1-FR11):

### Core Gameplay
* **FR1: Show the full pack:** Display all 52 cards in the deck.
* **FR2: Shuffle the cards:** Randomise the order of cards in the deck.
* **FR3: Deal card operations:** Deal the top card from the deck to create a new pile.

### Moves and Combinations
* **FR4: Adjacent pile moves:** Move the last pile onto the previous one if the top cards match.
* **FR5: Two-pile jump moves:** Move the last pile over two positions (onto the third-to-last pile) if the top cards match.
* **FR6: Amalgamate operations:** Combine any two specified piles in the middle when they match.

### Auto-Play and Score Management
* **FR7: Display cards in play:** Show the current state of all piles and their top cards.
* **FR8: Auto-play single move:** Makes the best possible move automatically, prioritizing two-pile jumps.
* **FR9: Auto-play multiple moves:** Automatically performs a sequence of moves, dealing new cards when no moves are possible.
* **FR10: Score management:** Displays the top ten player scores, sorted from lowest (best) to highest pile count.
* **FR11: Game exit operations:** Finalizes the game and records the score upon exit.

---

## 📐 Design Overview (UML Class Structure)

The project utilizes robust **Object-Oriented Programming (OOP)** principles, particularly **abstraction and inheritance**, with the abstract `CardAction` class serving as the superclass for card collections.

| Class | Role | Extends | Key Responsibilities |
| :--- | :--- | :--- | :--- |
| **`Application`** | Main CLI Interface/Control | | Manages game state, piles, `startGame()`, `dealCard()`, `amalgamatePiles()`. |
| **`CardAction`** | Abstract Superclass | | Common functions for card collections: `addCard()`, `removeTopCard()`, `isEmpty()`, `size()`. |
| **`Pack`** | The Deck (52 cards) | `CardAction` | Initializes a standard deck, `shuffle()`, `showAllCards()`. |
| **`Pile`** | Cards in Play | `CardAction` | Checks if it `canCombine(Pile)`, merges with `combineWith(Pile)`. |
| **`ScoreBoard`** | Score Persistence | | Loads/saves player scores to `scoreBoard.txt`, `showTopTen()`. |
| **`Player`** | Player Record | | Stores name and score, comparable for sorting via `compareTo()`. |
| **`Card`** | Individual Card | | Stores `suit` and `value` properties. |

### Auto-Play Logic

The `playOnce` method (FR8) executes the most **optimal move** based on the following priority, demonstrating algorithmic game logic:

1.  Check for **Two-Pile Jump** (FR5)
2.  Check for **Adjacent Pile Move** (FR4)
3.  Check for **Middle Pile Amalgamation** (FR6)

---

## 💻 How to Run

*(Please replace these placeholder instructions with the actual compilation and execution steps for your Java project.)*

1.  **Clone the repository:**
    ```bash
    git clone [YOUR_REPO_URL]
    cd patience-card-game
    ```
2.  **Compile and Run:**
    ```bash
    # Example: Compile the Java source files
    javac *.java 

    # Example: Run the main Application class (CLI)
    java Application
    ```

### CLI Menu

Upon execution, the game displays the main menu for user interaction:

=====PATIENCE CARD GAME MENU=====
Show the full pack ...
Amalgamate piles in the middle (by giving their numbers)
Show all cards in play ...
Show top ten results Q. Quit Enter option:

---

## 📈 Evaluation and Future Work

### Development Notes
* The implementation of **middle pile amalgamation** was noted as challenging due to the complexity of managing array indices when removing a pile and ensuring the remaining piles maintain order.
* The project demonstrates **solid OOP principles** and successfully integrates score management with text file persistence, and the auto-play system fulfills advanced requirements.

### Future Enhancements
* **GUI Improvement:** The primary area for improvement is to enhance the JavaFX GUI to display **all cards within a pile** rather than just the top card, which will significantly improve the user experience.

---

## 🎓 Academic Context

This project was completed as part of the **CS12320 Main Individual Assignment**. It serves as a practical demonstration of Java programming fundamentals, object-oriented design, and algorithm implementation for game logic.

