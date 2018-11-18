package test;
import java.util.*;

public class Tesy implements Runnable {
	int name;
	List<Integer> cards =   Collections.synchronizedList(new ArrayList<>());
	
	public Tesy(int name, List<Integer> cards) {
		this.name = name;
		this.cards = cards;
	}
	
	public Tesy delete(Tesy t) {
		t.cards.remove(0);
		return t;
	}
	
	private void printer(int name,List<Integer> l) {
		System.out.print("\n"+"PLAYER "+name+":");
		for(int i:l) {
			System.out.print(" "+i+",");
		}
	}
	public synchronized void run() {

			try {
				synchronized(this){
				cards.remove(0);
				printer(name, cards);
				}
			} catch (Exception e){}
				
	}
		
}
	
