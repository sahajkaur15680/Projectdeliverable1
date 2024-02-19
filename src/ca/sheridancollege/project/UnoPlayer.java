/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class UnoPlayer extends Player {
    private ArrayList<UnoCard> hand;

    public UnoPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public void addToHand(UnoCard card) {
        hand.add(card);
    }

    public UnoCard playCard(int index) {
        return hand.remove(index);
    }

    public ArrayList<UnoCard> getHand() {
        return hand;
    }

    @Override
    public void play() {
        // Logic to play Uno
    }
}
