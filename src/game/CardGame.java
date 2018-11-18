package game;

import java.util.*;
import java.io.*;

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
	public static List<DeckPlayer> deal(int n, Pack pack) throws IOException {
		List<DeckPlayer> deckPlayers = new ArrayList<DeckPlayer>();
		for(int i =1; i<=n; i++) {
			List<Card> playerCards = new ArrayList<Card>();
			List<Card> deckCards = new ArrayList<Card>();
			DeckPlayer deckPlayer = new DeckPlayer(i,playerCards,deckCards);
			deckPlayers.add(deckPlayer);
			String filename = "player_"+i+".txt";
			PrintWriter writer = new PrintWriter(filename);
			
			
		}
		for(int i = 1; i<= n; i++) {
			for(DeckPlayer d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.playerCards.add(top);
				pack.cards.remove(0);
				
			}	
		}
		for(DeckPlayer d: deckPlayers) {
			String filename = "player_"+d.name+".txt";
			try{
					FileWriter fw = new FileWriter(filename, true);
					BufferedWriter bw = new BufferedWriter(fw);
					
					
						int card1= d.playerCards.get(0).val;
						int card2 = d.playerCards.get(1).val;;
						int card3 = d.playerCards.get(2).val;;
						int card4 = d.playerCards.get(3).val;;
								
						String initial = "player "+d.name+" initial hand "+" "+card1+" "+card2+" "+card3+" "+card4;
						bw.write(initial);
						bw.close();
					} 
		
					catch(IOException e) {
						
					}
					
		}
		for(int i = 1; i<= n; i++) {
			for(DeckPlayer d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.deckCards.add(top);
				pack.cards.remove(0);
				
			}
		}
		return deckPlayers;		
			
	}

	

	public void hasWon() {

	}


	//MAIN METHOD//
	public static void main(String[] args) throws IOException {
		System.out.printf("Enter number of players: ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		Pack.checkPack(n);
		Pack pack = GeneratePack(n);
		List<DeckPlayer> l =Collections.synchronizedList( deal(n, pack));
			
	
		
		for(DeckPlayer p: l) {
			System.out.println();
			System.out.print("PLAYER "+p.name+": ");
			for(Card c:p.playerCards) {
				System.out.print(" "+c.val+",");
				
		
			}
			System.out.print("\n"+"Deck "+p.name+": ");
			for(Card c:p.deckCards) {
				System.out.print(" "+c.val+",");
				
		
			}
		}
		Thread t1 = new Thread(new CardThread(1,l));
		Thread t2 = new Thread(new CardThread(2,l));
		Thread t3 = new Thread(new CardThread(3,l));
		Thread t4 = new Thread(new CardThread(4,l));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
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
	}
