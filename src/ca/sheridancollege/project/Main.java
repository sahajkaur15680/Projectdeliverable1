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

public class Main {
    public static void main(String[] args) {
        UnoGame unoGame = new UnoGame("Uno");
        UnoPlayer player1 = new UnoPlayer("Player 1");
        UnoPlayer player2 = new UnoPlayer("Player 2");

        unoGame.addPlayer(player1);
        unoGame.addPlayer(player2);

        unoGame.play();
        unoGame.declareWinner();
    }
}
