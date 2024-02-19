/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author Dharampreet Singh
 * @author Ravneet Kaur
 * @author Sahajpreet Kaur
 * @author Harshpreet Singh
 * 18 Jan 2020
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame extends Game {
    private UnoDeck deck;
    private ArrayList<UnoPlayer> players;
    private UnoCard.Color currentColor;
    private UnoCard.Value currentValue;
    private boolean directionClockwise; // true for clockwise, false for counterclockwise
    private int currentPlayerIndex;

    public UnoGame(String name) {
        super(name);
        deck = new UnoDeck();
        players = new ArrayList<>();
        directionClockwise = true; // Start with clockwise direction
        currentPlayerIndex = 0; // Start with the first player
    }

    public void addPlayer(UnoPlayer player) {
        players.add(player);
    }

    @Override
    public void play() {
        // Deal 7 cards to each player
        for (UnoPlayer player : players) {
            for (int i = 0; i < 7; i++) {
                player.addToHand(deck.draw());
            }
        }

        // Start the game
        boolean gameEnded = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            UnoPlayer currentPlayer = players.get(currentPlayerIndex);

            // Display current player's hand and top card on discard pile
            System.out.println("Current Player: " + currentPlayer.getName());
            System.out.println("Top Card: " + deck.getTopCard());

            // Display current player's hand
            System.out.println("Your Hand:");
            for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                System.out.println((i + 1) + ". " + currentPlayer.getHand().get(i));
            }

            // Allow player to play a card
            System.out.println("Choose a card to play (enter index) or draw a card (enter 0):");
            int choice = scanner.nextInt();

            if (choice == 0) {
                // Draw a card from the deck
                UnoCard drawnCard = deck.draw();
                currentPlayer.addToHand(drawnCard);
                System.out.println("You drew: " + drawnCard);
            } else {
                UnoCard selectedCard = currentPlayer.playCard(choice - 1);
                if (isValidPlay(selectedCard)) {
                    // Update current color and value if needed
                    if (selectedCard.getValue() != UnoCard.Value.WILD && selectedCard.getValue() != UnoCard.Value.WILD_DRAW_FOUR) {
                        currentColor = selectedCard.getColor();
                        currentValue = selectedCard.getValue();
                    }
                    // Handle special card effects
                    handleSpecialCard(selectedCard);
                    // Put the played card on top of the discard pile
                    deck.addToDiscardPile(selectedCard);
                    System.out.println("You played: " + selectedCard);
                } else {
                    // Invalid play, draw a card
                    System.out.println("Invalid play! You must draw a card.");
                    UnoCard drawnCard = deck.draw();
                    currentPlayer.addToHand(drawnCard);
                    System.out.println("You drew: " + drawnCard);
                }
            }

            // Check if the player has won
            if (currentPlayer.getHand().isEmpty()) {
                gameEnded = true;
            }

            // Move to the next player
            moveNextPlayer();
        }

        scanner.close();
    }

    @Override
    public void declareWinner() {
        UnoPlayer winner = players.get(currentPlayerIndex);
        System.out.println("Congratulations! " + winner.getName() + " wins!");
    }

    private boolean isValidPlay(UnoCard card) {
        UnoCard topCard = deck.getTopCard();
        return card.getColor() == currentColor || card.getValue() == currentValue || card.getColor() == UnoCard.Color.WILD || topCard.getColor() == UnoCard.Color.WILD;
    }

    private void handleSpecialCard(UnoCard card) {
        // Handle special card effects
    }

    private void moveNextPlayer() {
        if (directionClockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }
}
