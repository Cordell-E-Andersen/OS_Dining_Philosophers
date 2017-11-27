package eatingPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cordell: Controls the queue of starvation so that the philosophers
 *         will not starve
 */

public class StarvingQueue {
	private final List<Philosopher> starvingPeople; // creates array of philosophers that are starving

	StarvingQueue() {
		starvingPeople = new ArrayList<Philosopher>(); // list of people next in the starving queue
	}

	/**
	 * Adds philosopher to the starvation queue
	 * 
	 * @param p
	 */
	public synchronized void addPhilosopher(Philosopher p) {
		starvingPeople.add(p); // adds a starving phil to the queue
		if (countPhilosophers() == 1) {
			try {
				notifyAll();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Sets a priority on a starving philosopher
	 * 
	 * @return starvingPeople.get
	 */
	public synchronized Philosopher getFirst() {
		return starvingPeople.get(0); //assures that the starving people go to the top of the queue for next to try and eat
	}

	/**
	 * Removes the philosopher from the starvation queue
	 */
	public synchronized void takeOutPhilosopher() { //assures that once someone has eaten they are removed from the queue
		while (countPhilosophers() == 0) {
			try {
				wait();
			} catch (InterruptedException ignored) {
			}
		}
		starvingPeople.remove(0);

	}

	/**
	 * counts the number of people in the queue
	 * 
	 * @return starvingPeople.size
	 */
	public synchronized int countPhilosophers() {
		return starvingPeople.size();
	}

}
