package game;

import java.util.*;

public class DeckPlayer {
	
	List<Card> deckCards = new ArrayList<Card>();
	List<Card> playerCards = new ArrayList<Card>();
	int name;
	
	
	public DeckPlayer(int name,List<Card> deckCards, List<Card> playerCards) {
		this.name = name;
		this.deckCards = deckCards;
		this.playerCards = playerCards;		
	}
}
