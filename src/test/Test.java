package test;
import java.util.*;

 class Test {
	
	public static void main(String[]args) {
		List<Integer> listy =  new ArrayList<Integer>();
		listy.add(0);
		listy.add(1);
		listy.add(2);
		listy.add(3);
		listy.add(4);
		listy.add(5);
		int name1 = 1;
		int name2 = 2;

		Thread t1  = new Thread(new Tesy(name1,listy));
		Thread t2 = new Thread(new Tesy(name2,listy));
		
		t1.start();
		t2.start();

		
	}
	
}
 class Tesy implements Runnable {
	int name;
	List<Integer> cards =   new ArrayList<Integer>();
	
	public Tesy(int name, List<Integer> cards) {
		this.name = name;
		this.cards = cards;
	}
	
	
	private static synchronized void printer(int name,List<Integer> l) {
		System.out.print("\n"+"PLAYER "+name+":");
		for(int i:l) {
			System.out.print(" "+i+",");
		}
	}
	public void run() {

			try {
				cards.remove(0);
				printer(name, cards);
				
			} catch (Exception e){}
				
	}
		
}
