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
import java.util.Collections;

public class UnoDeck extends GroupOfCards {
    private ArrayList<UnoCard> discardPile;

    public UnoDeck() {
        super(108); // Uno deck has 108 cards
        initializeDeck();
        discardPile = new ArrayList<>();
    }

    private void initializeDeck() {
        for (UnoCard.Color color : UnoCard.Color.values()) {
            if (color != UnoCard.Color.WILD) {
                for (UnoCard.Value value : UnoCard.Value.values()) {
                    if (value != UnoCard.Value.WILD && value != UnoCard.Value.WILD_DRAW_FOUR) {
                        UnoCard card = new UnoCard(color, value);
                        getCards().add(card);
                    }
                }
            }
        }
        // Add four copies of each Wild and Wild Draw Four card
        for (int i = 0; i < 4; i++) {
            getCards().add(new UnoCard(UnoCard.Color.WILD, UnoCard.Value.WILD));
            getCards().add(new UnoCard(UnoCard.Color.WILD, UnoCard.Value.WILD_DRAW_FOUR));
        }
        shuffle(); // Shuffle the deck after populating it
    }

    public UnoCard draw() {
        if (getCards().isEmpty()) {
            reshuffleDiscardPile();
        }
        return (UnoCard) getCards().remove(0); // Cast the Card object to UnoCard
    }

    public UnoCard getTopCard() {
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.get(discardPile.size() - 1);
    }

    public void addToDiscardPile(UnoCard card) {
        discardPile.add(card);
    }

    private void reshuffleDiscardPile() {
        // Move cards from discard pile to deck, shuffle, and clear the discard pile
        getCards().addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(getCards());
    }
}
