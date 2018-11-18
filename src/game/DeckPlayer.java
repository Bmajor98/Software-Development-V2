package game;

import java.util.*;

public class DeckPlayer {
	
	List<Card> dCards = new ArrayList<Card>();
	List<Card> pCards = new ArrayList<Card>();
	int dName;
	int pName;	
	public Deck deck = new Deck(pName, dCards);
	public Player player = new Player(pName, pCards);
	
	
	public DeckPlayer(Deck deck, Player player) {
		this.deck = deck;
		this.player = player;		
	}
}
