/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
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

public abstract class Game {

    private final String name;
    private ArrayList<Player> players;
    private Scanner input;

    public Game(String name) {
        this.name = name;
        players = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract void play();

    public abstract void declareWinner();

    // Method to register a player with the game
    public void registerPlayer() {
        System.out.print("Enter player name: ");
        String playerName = input.nextLine();
        Player player = new UnoPlayer(playerName); // Assuming UnoPlayer is a concrete implementation of Player
        players.add(player);
        System.out.println(playerName + " has been registered with the game.");
    }
}