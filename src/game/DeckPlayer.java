package game;

import java.util.*;

public class DeckPlayer {

	public List<Player> players = new ArrayList<Player>();
	public List<Deck> decks = new ArrayList<Deck>();
	
	public DeckPlayer(List<Player> players, List<Deck> decks) {
		this.players = players;
		this.decks = decks;
	}
	
}
