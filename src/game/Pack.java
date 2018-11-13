package game;

import java.util.*;


public class Pack {
	
	List<Card> cards = new ArrayList<Card>();
	
	public Pack(List<Card> cards) {
		this.cards = cards;
	}
	
	public static Pack GeneratePack(int PlayerNum) {
		List<Card> packList = new ArrayList<Card>();
		for(int i = 1; i < PlayerNum; i++) {
			for(int j = 1; j <= 8;j++) {
				Card card = new Card(i);
				packList.add(card);
			}			
		}
		Collections.shuffle(packList);
		Pack pack = new Pack(packList);
		return pack;
	}
	
	public static DeckPlayer Deal(int n, Pack pack) {
		List<Player> players = new ArrayList<Player>();
		List<Deck> decks = new ArrayList<Deck>();
		
		for(int i = 1; i <= n; i++) {
			Player p = new Player(i,null);
			players.add(p);
		}
		for(int i = 1; i <= 4; i++) {
			for(Player p: players) {
				Card top = pack.cards.get(0);
				System.out.println(top.val);
				p.cards.add(top);
				pack.cards.remove(0);
			}
		}		
		for(int i = 1; i <= n; i++) {
			Deck d = new Deck(i,null);
			decks.add(d);
		}
		for(int i = 1; i <= 4; i++) {
			for(Deck d: decks) {
				Card top = pack.cards.get(0);
				d.cards.add(top);
				pack.cards.remove(0);				
			}
		}
		
		DeckPlayer dP = new DeckPlayer(players,decks);
		return dP;
	}
	
	public static void main(String[] args) {
		int n  = 4;
		Pack pack = GeneratePack(n);
		DeckPlayer dP = Deal(n,pack);
		List<Player> players = dP.players;
		List<Deck> decks = dP.decks;
		
		Player p1 = players.get(0);
		for(Card c: p1.cards) {
			int val = c.val;
			System.out.println(val);
		}
		
		
	}
}
