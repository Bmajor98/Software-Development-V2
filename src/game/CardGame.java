package game;

import java.util.*;

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
	public static List<DeckPlayer> Deal(int n, Pack pack) {
		List<DeckPlayer> deckPlayers = new ArrayList<DeckPlayer>();
		for(int i =1; i<=n; i++) {
			List<Card> playerCards = new ArrayList<Card>();
			List<Card> deckCards = new ArrayList<Card>();
			DeckPlayer deckPlayer = new DeckPlayer(i,playerCards,deckCards);
			deckPlayers.add(deckPlayer);
		}
		for(int i = 1; i<= n; i++)
			for(DeckPlayer d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.playerCards.add(top);
				pack.cards.remove(0);
				
			}
		for(int i = 1; i<= n; i++)
			for(DeckPlayer d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.deckCards.add(top);
				pack.cards.remove(0);
				
			}
		return deckPlayers;		
			
	}


	public static List<DeckPlayer> Draw(int name, List<DeckPlayer> deckPlayers) {
		int listIndex = name -1;
		DeckPlayer drawPlayer = deckPlayers.get(listIndex);
		Card topDeck = deckPlayers.get(listIndex).deckCards.get(0);
		drawPlayer.playerCards.add(topDeck);
		drawPlayer.deckCards.remove(0);
		deckPlayers.set(listIndex, drawPlayer);	
		return deckPlayers;

	}
	
	public static int Choose(DeckPlayer dP) {
		Random rand = new Random();
		boolean t = true;
		int index = 0;
		while(t) {
			index = rand.nextInt(4);
			if(dP.playerCards.get(index).val != dP.name) {
				t = false;
	
			}
		}
		return index;
	}
	
	public static List<DeckPlayer> Discard(int index, int name, List<DeckPlayer> deckPlayers) {
		int listIndex = name -1;
		DeckPlayer discardPlayer = deckPlayers.get(listIndex);
		Card discardCard = discardPlayer.playerCards.get(index);
		discardPlayer.playerCards.remove(index);
		deckPlayers.set(listIndex, discardPlayer);
		
		if(name == deckPlayers.size()) {
			DeckPlayer gainCard = deckPlayers.get(0);
			gainCard.deckCards.add(discardCard);
			deckPlayers.set(0, gainCard);
			
		} else {
			DeckPlayer gainCard = deckPlayers.get(listIndex + 1);
			gainCard.deckCards.add(discardCard);
			deckPlayers.set(listIndex +1, gainCard);
			
		}
	
		return deckPlayers;
	
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
		List<DeckPlayer> l = Deal(n, pack);
		

		for(DeckPlayer p: l) {
			System.out.println();
			System.out.print("PLAYER "+p.name+": ");
			for(Card c:p.playerCards) {
				System.out.print(" "+c.val+",");
				
		
			}
		}
		
		
		
		

	}
	
//	public static boolean Check(Player player) {
//		List<Integer> hand = new ArrayList<Integer>();
//		for(Card card: player.cards) {
//			hand.add(card.val);
//		}		
//		Stream<Integer> values = hand.stream();
//		if(values.distinct().count() ==1) {
//			return true;
//		}
//		return false;
//			

	}
