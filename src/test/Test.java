package test;
import java.util.*;

public class Test {
	
	public static void main(String[]args) {
		List<Integer> listy =  Collections.synchronizedList(new ArrayList<Integer>());
		listy.add(0);
		listy.add(1);
		listy.add(2);
		listy.add(3);
		listy.add(4);
		listy.add(5);
		int name1 = 1;
		int name2 = 2;
		Tesy t = new Tesy(name1, listy);
		Thread t1  = new Thread(new Tesy(name1,listy));
		Thread t2 = new Thread(new Tesy(name2,listy));
		
		t1.start();
		t2.start();

		
	}
	
}