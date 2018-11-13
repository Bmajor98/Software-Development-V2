package game;

import java.util.*;


public class Pack {
	
	List<Card> cards = new ArrayList<Card>();
	
	public Pack(List<Card> cards) {
		this.cards = cards;
	}
	
	
	
//	public static CardsPack Generate(int n, Pack pack){
//		List<List<Card>> cards = new ArrayList<List<Card>>();
//		for(int i = 1; i <= n; i++) {
//			List<Card>  c = new ArrayList<Card>();
//			cards.add(c);
//		}
//		
//		for(int i = 1; i <= n; i++) {
//			for(List<Card> l : cards) {
//				Card top = pack.cards.get(0);
//				l.add(top);
//				pack.cards.remove(0);
//			}
//		}
//		CardsPack cardsPack = new CardsPack(cards,pack);
//		return cardsPack;
//	}
		
	}

