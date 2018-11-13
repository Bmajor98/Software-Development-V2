package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Player {

	List<Card> cards = new ArrayList<Card>();
	private int name;

	public Player(int name, List<Card> cards ) {
		this.cards = cards;
		this.name = name;////-
	}

	public void Draw() {
		 
	 }
	 
	public void Discard() {
		 
	 }
	
	
	public static boolean Check(Player player) {
		List<Integer> hand = new ArrayList<Integer>();
		for(Card card: player.cards) {
			hand.add(card.val);
		}		
		Stream<Integer> values = hand.stream();
		if(values.distinct().count() ==1) {
			return true;
		}
		return false;
			
	}
}