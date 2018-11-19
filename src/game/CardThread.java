package game;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CardThread implements Runnable {
	int name;
	volatile List<DeckPlayer> l = new ArrayList<DeckPlayer>();
	List<Boolean> halter = new ArrayList<Boolean>();
	private volatile boolean flag = true;
	
	CardThread(int name,List<DeckPlayer> l,List<Boolean> halter){
		this.name = name;
		this.l = l;
		this.halter = halter;
	}
	
	public static List<DeckPlayer> draw(int name, List<DeckPlayer> deckPlayers) {
		int listIndex = name -1;
		DeckPlayer drawPlayer = deckPlayers.get(listIndex);
		Card topDeck = deckPlayers.get(listIndex).deckCards.get(0);
		if(topDeck != null) {
			
			drawPlayer.playerCards.add(topDeck);
			drawPlayer.deckCards.remove(0);
			deckPlayers.set(listIndex, drawPlayer);	
		}
		
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
	public synchronized static  void currentHand(DeckPlayer dP){
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
	
	public void stopRunning() {
		flag = false;
	}


	public void run(){
		try {		
			synchronized(l) {
			
				while(!check(name,l)) {
				for(int i = 1; i<=10;i++) {
					turn(name,l);
					check(name,l);
					if(check(name,l)) {
						halter.add(true);		
					}
					if(halter.size()>0) {
						this.stopRunning();
					}
				}
			}
				
						
			}
			}catch(Exception e) {}
		
			}
	
}

