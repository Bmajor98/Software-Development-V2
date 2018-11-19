/**
 * This class defines the player object. A player consists of a list of playerCards -
 * a list of cards that if matching will win a game, and a list of deckCards, a list of
 * cards that a play can draw from.
 */
package game;

import java.util.*;

public class Player {
	
	List<Card> deckCards = new ArrayList<Card>();
	List<Card> playerCards = new ArrayList<Card>();
	int name;
	
	
	public Player(int name,List<Card> deckCards, List<Card> playerCards) {
		this.name = name;
		this.deckCards = deckCards;
		this.playerCards = playerCards;		
	}
}
