/**
 * This class contains the methods that threads will use to play the game. All
 * of these methods are static and all return a list of players. This list is mutable 
 * and is accessed by multiple threads, therefore action on the list is synchronized.
 * This class also defines the thread objects that play the game. These will continue to play
 * until a thread has won. The flag used is a volatile list. Threads add a true variable to the list
 * if they win. This change in list alerts the other threads to stop.
 */
package game;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CardThread implements Runnable {
	int name;
	volatile List<Player> l = new ArrayList<Player>();
	List<Boolean> halter = new ArrayList<Boolean>();
	private volatile boolean flag = true;
	
	CardThread(int name,List<Player> l,List<Boolean> halter){
		this.name = name;
		this.l = l;
		this.halter = halter;
	}
	
	/**
	 * This method is used by threads to draw a card. Using the corresponding player object
	 * in the list with the same name as the thread, it takes a card from the Player's deck (deckCards)
	 * and adds it to the Player's hand (playerCards). In addition this method writes to the Player's corresponding
	 * .txt file what happened in the draw move.
	 * @param name the thread name
	 * 
	 * @param players shared list of players
	 * @return list of players
	 */
	public static List<Player> draw(int name, List<Player> players) {
		int listIndex = name -1;
		Player drawPlayer = players.get(listIndex);
		Card topDeck = players.get(listIndex).deckCards.get(0);
		if(topDeck != null) {
			
			drawPlayer.playerCards.add(topDeck);
			drawPlayer.deckCards.remove(0);
			players.set(listIndex, drawPlayer);	
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
		
		return players;
		}
	
	/**
	 * This method is used by threads to discard a card. Using  Using the corresponding player object
	 * in the list with the same name as the thread, it takes a card from the Player's hand (playerCards)
	 * and adds it to the next Player's deck(deckCards). The Player receiving the card is the player with 
	 * the name 1 higher than the discarding player, or if the discarding player has the highest name, the 
	 * receiving player is the player with the lowest name. In addition this method writes to the Player's corresponding
	 * .txt file what happened in the discard move.
	 * 
	 * @param name the thread name
	 * @param players shared list of players
	 * @return list of players
	 */
	public static List<Player> discard(int name, List<Player> players) {
		int listIndex = name -1;
		
		Player discardPlayer = players.get(listIndex);
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
		players.set(listIndex, discardPlayer);
		
		if(name == players.size()) {
			Player gainCard = players.get(0);
			gainCard.deckCards.add(discardCard);
			players.set(0, gainCard);
			
		} else {
			Player gainCard = players.get(listIndex + 1);
			gainCard.deckCards.add(discardCard);
			players.set(listIndex +1, gainCard);	

		}
		
		String filename  = "player_"+name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			int card = discardCard.val;
			int discard;
			if(name == players.size()) {
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
		return players;
		}
	
	/**
	 * This method writes the Player's current hand to the Player's corresponding .txt file.
	 * 
	 * @param player the player whose hand is being written to the file.
	 */
	public synchronized static  void currentHand(Player player){
		String filename = "player_"+player.name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int card1 = player.playerCards.get(0).val;
			int card2 = player.playerCards.get(1).val;
			int card3 = player.playerCards.get(2).val;
			int card4 = player.playerCards.get(3).val;
					
			String currentHand = "player "+player.name+" current hand "+" "+card1+" "+card2+" "+card3+" "+card4;
			bw.write(currentHand);
			bw.newLine();
			bw.close();
		}catch(IOException e){}
	}
	
	/**
	 * This method writes the Player's current deck to the Player's corresponding .txt file.
	 * 
	 * @param player the player whose deck is being written to the file. 
	 */
	public synchronized static  void currentDeck(Player player){
		String filename = "player_"+player.name+".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int card1 = player.deckCards.get(0).val;
			int card2 = player.deckCards.get(1).val;
			int card3 = player.deckCards.get(2).val;
			int card4 = player.deckCards.get(3).val;
					
			String currentHand = "player "+player.name+" deck "+" "+card1+" "+card2+" "+card3+" "+card4;
			bw.write(currentHand);
			bw.newLine();
			bw.close();
		}catch(IOException e){}
	}
	
	/**
	 * This method combines the draw,discard and currentHand method into a single synchronized
	 * static method so that drawing and discarding behaves as an atomic action.
	 * 
	 * @param name the thread name
	 * @param players
	 * @return players shared list of players
	 */
	 private  synchronized static  List<Player> turn(int name, List<Player> players) {
		players = draw(name, players);
		players = discard(name,players);
		currentHand(players.get(name-1));
		return players;
	 }
	synchronized public static boolean check(int name,List<Player> dP) {
		
		List<Card> hand = dP.get(name-1).playerCards;
		boolean allEqual = hand.stream().distinct().limit(2).count() <= 1;
		return allEqual;
		}
	
	public void stopRunning() {
		flag = false;
	}

	/**
	 * The run method is what the thread players execute. While no player has won each
	 * thread will continue to take turns - executing the turn method. When a thread has won
	 * the flag - List<Boolean> - has a true variable added to it. All threads cease commencing their 
	 * turns and the winner has his winning hand written to his corresponding .txt file. 
	 */
	public void run(){
		try {		
			synchronized(l) {
			
				while(!check(name,l)) {
		 {
					turn(name,l);
					check(name,l);
					if(check(name,l)) {
						currentDeck(l.get(name-1));
						halter.add(true);
						String filename = "player_"+name+".txt";
						FileWriter fw =new FileWriter(filename);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.newLine();
						bw.write("Player "+name+" wins");
						bw.close();
						currentHand(l.get(name-1));
							
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

