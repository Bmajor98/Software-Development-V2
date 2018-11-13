package game;

import java.io.File;
import java.util.*;


public class Pack {
	
	List<Card> cards = new ArrayList<Card>();
	
	public Pack(List<Card> cards) {
		this.cards = cards;

		
	
}

private static Scanner x;
public static void checkPack(int n) {
	
	try {
		x = new Scanner(new File("pack.txt"));
		int cardCount = 0;
		try {
			while (x.hasNext()) {
				int a = Integer.parseInt(x.nextLine());
				if (a > 0) {
					cardCount ++;
				} else {
					throw new Exception();
				}
			}
			if (cardCount / n == 8) {
				System.out.println("File is okay, pack contains " + cardCount + " cards");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.out.println("Invalid pack file");
			System.exit(1);
		}
	} catch(Exception e) {
		System.out.println("Pack file not found");
		System.exit(1);
	}
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

