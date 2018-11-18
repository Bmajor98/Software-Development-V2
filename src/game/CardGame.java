package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CardGame {
	
	//GENERATE PACK//
	public static Pack GeneratePack(int PlayerNum) {
		List<Card> packList = new ArrayList<Card>();
		for(int i = 1; i <= PlayerNum; i++) {
			for(int j = 1; j <= 8;j++) {
				Card card = new Card(i);
				packList.add(card);
			}			
		}
		Collections.shuffle(packList);
		Pack pack = new Pack(packList);
		System.out.println("PACK SIZE: "+pack.cards.size());
		return pack;
	}
	
	//DEAL PLAYER//
	public static PlayerPack DealPlayer(int n, Pack pack) {
		List<Player> players = new ArrayList<Player>();
		
		for(int i = 1; i <= n; i++) {
			List<Card> cards = new ArrayList<Card>();
			Player p = new Player(i,cards);
			players.add(p);
		}			
		
		for(int i = 1; i <= n ; i++) {	
			for(Player p: players) {
				Card top = pack.cards.get(0);
				p.cards.add(top);
				pack.cards.remove(0);
			}		
		}		
		PlayerPack pP = new PlayerPack(players, pack);
		return pP;
	}
	
	//DEAL DECK//
	public static List<Deck> DealDeck(int n, Pack pack) {
		List<Deck> decks = new ArrayList<Deck>();
		
		for(int i = 1; i <= n; i++) {
			List<Card> cards = new ArrayList<Card>();
			Deck d = new Deck(i,cards);
			decks.add(d);
		}
				
		for(int i = 1; i <= n ; i++) {	
			for(Deck d: decks) {
				Card top = pack.cards.get(0);
				d.cards.add(top);
				pack.cards.remove(0);
			}	
		}	
		return decks;
	}
	
	public static DeckPlayer Draw(DeckPlayer dP) {
		Card topDeck = dP.deck.cards.get(0);
		dP.player.cards.add(topDeck);
		dP.deck.cards.remove(0);
		return dP;
	 
	}
	
	public void Discard() {
		 
	}
	
	public void hasWon() {
		
	}
	
	
	//MAIN METHOD//	
	public static void main(String[] args) {
		System.out.printf("Enter number of players: ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); 
		scanner.close();
		Pack.checkPack(n);
		Pack pack = GeneratePack(n);
		PlayerPack pP = DealPlayer(n,pack);
		List<Player> players = pP.players;
		List<Deck> decks = DealDeck(n,pP.pack);
		
		for(Player p: players) {
			System.out.println();
			System.out.print("PLAYER "+p.name+": ");
			for(Card c:p.cards) {
				System.out.print(" "+c.val+",");
			}
		}
		for(Deck d: decks) {
			System.out.println();
			System.out.print("DECK "+d.name+": ");
			for(Card c:d.cards) {
				System.out.print(" "+c.val+",");
			}
		}

		List<DeckPlayer> deckPlayerList = new ArrayList<DeckPlayer>();
		for(int i = 0; i <= n-1; i++) {
			Deck deck = decks.get(i) ;
			Player player = players.get(i);	
			DeckPlayer dp = new DeckPlayer(deck,player);
			deckPlayerList.add(dp);
		}
		DeckPlayer dp = Draw(deckPlayerList.get(0));
		for(Card c: dp.get(0.));

	}	
}
