package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Card> cards = new ArrayList<Card>();
	private int name;

	public Player(int name, List<Card> cards ) {
		this.cards = cards;
		this.name = name;
	}

public void Draw() {
	 
 }
 
 public void Discard() {
	 
 }
}