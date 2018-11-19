/**
 * 
 *This is the main class that will be executing the running of the game. 
 * The class consists of static methods and the Main method.
 *
 */
package game;
import java.util.*;
import java.io.*;

public class CardGame {
	/**
	 * Reads a pack.txt file and returns a list of card objects (a Pack) with card
	 * values  corresponding to numbers in pack.txt file.
	 * @return Pack of cards
	 * @throws IOException
	 */
	public static Pack packMaker() throws IOException {
		String filename = "pack.txt";
		FileReader fr = new FileReader(filename);
		List<Card> cardList = new ArrayList<Card>();
		try (BufferedReader br = new BufferedReader(fr)) {
		      String line;
		      while((line = br.readLine()) != null) {
		        int value = Integer.parseInt(line);
		        Card card = new Card(value);
		        cardList.add(card);
		      	}
		      }
		Pack pack = new Pack(cardList);
		System.out.println("PACK SIZE: "+pack.cards.size());
		return pack;
		
	}

	/**
	 * Deals cards from the pack to players and decks - sequentially respectively.
	 * @param n - number of players
	 * @param pack - pack of cards
	 * @return List<Player> - all the players and their corresponding decks
	 * @throws IOException
	 */
	public static List<Player> deal(int n, Pack pack) throws IOException {
		List<Player> deckPlayers = new ArrayList<Player>();
		for(int i =1; i<=n; i++) {
			List<Card> playerCards = new ArrayList<Card>();
			List<Card> deckCards = new ArrayList<Card>();
			Player deckPlayer = new Player(i,playerCards,deckCards);
			deckPlayers.add(deckPlayer);
			String filename = "player_"+i+".txt";
			PrintWriter writer = new PrintWriter(filename);
			
			
		}
		for(int i = 1; i<= n; i++) {
			for(Player d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.playerCards.add(top);
				pack.cards.remove(0);
				
			}	
		}
		for(Player d: deckPlayers) {
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
			for(Player d: deckPlayers) {
				Card top = pack.cards.get(0);
				d.deckCards.add(top);
				pack.cards.remove(0);
				
			}
		}
		return deckPlayers;		
			
	}


	/**
	 * Main method. Executes the game by first asking user to input number of players and checking
	 *  and creating a pack, then creating the corresponding number of players. Creates and starts 
	 *  the threads which play the game.
 	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.printf("Enter number of players: ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		Pack pack = packMaker();
		Pack.checkPack(n);
		
		List<Player> l =Collections.synchronizedList( deal(n, pack));
		List<Boolean> booList = new ArrayList<Boolean>();
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i = 1;i<=n;i++) {
			Thread thready = new Thread(new CardThread(i,l,booList));
			threadList.add(thready);
			}
		

		for(Thread t: threadList) {
			t.start();
		}
	}
}



