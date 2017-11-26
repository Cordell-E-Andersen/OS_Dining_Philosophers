package eatingPackage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nate Williams
 * Original layout was fleshed out by Andrew Kerwin and I completed the modifications necessary for it to function
 * This class provides a list for workers add oranges to and take oranges from
 */
public class StarvingQueue {
	private final List<Philosopher> starvingPeople;
	
	StarvingQueue() {
		starvingPeople = new ArrayList<Philosopher>(); 
	}
	
	/**
	 * receives an orange and adds it to the list 
	 * @param o Orange
	 */
	public synchronized void addPhilosopher(Philosopher p) {
		starvingPeople.add(p);
		if (countPhilosophers() == 1) {
			try {
				notifyAll();
			} catch (Exception e) {}
		}
	}
	
	public synchronized Philosopher getFirst() {
		return starvingPeople.get(0);
	}
	
	/**
	 * removes the 0th element from the list
	 * @return Orange oranges[0]
	 */
	public synchronized void takeOutPhilosopher() {
		while (countPhilosophers() == 0) {
			try {
				wait();
			} catch (InterruptedException ignored) {}
		}
		starvingPeople.remove(0);
		//edited to actually remove the orange from the list
		
	}
	
	/**
	 * returns the number of oranges in the list
	 * @return int oranges.size()
	 */
	public synchronized int countPhilosophers() {
		return starvingPeople.size();
	}
	
}
