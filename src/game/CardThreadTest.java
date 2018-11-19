package game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;


import org.junit.Test;

public class CardThreadTest {
	
	Card card1 = new Card(1);
	Card card2 = new Card(2);
	Card card3 = new Card(3);
	Card card4 = new Card(4);
	
	
	List<Card> listcards = new ArrayList<Card>();
	
	
	
	Player player = new Player(1, new ArrayList<Card>(), listcards);
	
	ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
	
	

	@Test
	public void testDraw() {
		
		CardThread cardthread = new CardThread(0, players, null);
		assertNotNull(players);
	}

	@Test
	public void testDiscard() {
		fail("Not yet implemented");
	}
	@Test
	public void testCheckPack() {
		
	}

}
