package game;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	List<Card> cards = new ArrayList<Card>();
	int name;

	public Deck(int name, List<Card> cards ) {
		this.cards = cards;
		this.name = name;
	}

public void Draw() {
	 
 }
 
 public void Discard() {
	 
 }
}