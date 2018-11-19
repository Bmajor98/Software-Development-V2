package game;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CardThread implements Runnable {
	int name;
	volatile List<DeckPlayer> l = new ArrayList<DeckPlayer>();
	
	CardThread(int name,List<DeckPlayer> l){
		this.name = name;
		this.l = l;
	}
	
	public static List<DeckPlayer> draw(int name, List<DeckPlayer> deckPlayers) {
		int listIndex = name -1;
		DeckPlayer drawPlayer = deckPlayers.get(listIndex);
		Card topDeck = deckPlayers.get(listIndex).deckCards.get(0);
		drawPlayer.playerCards.add(topDeck);
		drawPlayer.deckCards.remove(0);
		deckPlayers.set(listIndex, drawPlayer);	
		
		String filename = "player_"+name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			int card = topDeck.val;
			
			String draw = "player "+name+ " draws a "+card+" from deck "+name;
			bw.newLine();
			bw.write(draw);
			bw.close();
			
		} catch(IOException e) {
			
		}
		
		return deckPlayers;

	}
	
	public static List<DeckPlayer> discard(int name, List<DeckPlayer> deckPlayers) {
		int listIndex = name -1;
		
		DeckPlayer discardPlayer = deckPlayers.get(listIndex);
		Random rand = new Random();
		List<Card> playerCards = discardPlayer.playerCards;
		boolean t = true;
		int index = 0;
		while(t) {
			index = rand.nextInt(4);
			if(playerCards.get(index).val != name) {
				t = false;
			}
		}
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
		
		String filename  = "player_"+name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			int card = discardCard.val;
			int discard;
			if(name == deckPlayers.size()) {
				discard = 1;
			} else {
				discard = name +1;
			}
			String draw = "player "+name+ " discards a "+card+" to deck "+(discard);
			bw.newLine();
			bw.write(draw);
			bw.newLine();
			bw.close();
			
		} catch(IOException e) {}
		return deckPlayers;
	}
	public static  void currentHand(DeckPlayer dP){
		String filename = "player_"+dP.name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int card1 = dP.playerCards.get(0).val;
			int card2 = dP.playerCards.get(1).val;
			int card3 = dP.playerCards.get(2).val;
			int card4 = dP.playerCards.get(3).val;
					
			String currentHand = "player "+dP.name+" current hand "+" "+card1+" "+card2+" "+card3+" "+card4;
			bw.write(currentHand);
			bw.newLine();
			bw.close();
		}catch(IOException e){}
	}
	
	 private  static  List<DeckPlayer> turn(int name, List<DeckPlayer> deckPlayers) {
		deckPlayers = draw(name, deckPlayers);
		deckPlayers = discard(name,deckPlayers);
		currentHand(deckPlayers.get(name-1));
		return deckPlayers;
	 }
	synchronized public static boolean check(int name,List<DeckPlayer> dP) {
		
		List<Card> hand = dP.get(name-1).playerCards;
		boolean allEqual = hand.stream().distinct().limit(2).count() <= 1;
		return allEqual;
		
	}
		
		
	

	public void run(){
		try {		
			synchronized(l){
				for(int i =0; i < 1000; i++) {
					turn(name,l);
							
						}
			}
						
			}catch(Exception e) {}
		
	}
	

}
