package eatingPackage;

/**
 * 
 * @author Cordell: code partially pulled from Nate's code: Cup creates the
 *         specified number of chopsticks and places them in a sterilized cup
 *         This class also controls the acquire and release methods for the
 *         philosophers obtaining two sticks
 * 
 */
public class Cup {
	private int stickCount;

	/**
	 * Creates varible stickCount declaring how many total sticks are available to
	 * grab
	 */
	public Cup() {
		stickCount = 5; //number of sticks to be created
	}

	/**
	 * Allows a philosopher to try and grab two chopsticks, and either they can grab
	 * two or none at all. This is in a critical section so it cannot be interrupted
	 * 
	 * @return grabbed
	 */
	public synchronized boolean acquire() { //synchonized cup of sticks
		boolean grabbed = false; 

		if (stickCount >= 2) { //if the number of available sticks is equal to or greater than 2 the phil can take 2
			stickCount = stickCount - 2; //removes 2 sticks from the cup
			grabbed = true; //phil successfully grabs 2 sticks
		}

		return grabbed; //returns if the phil succeeded in taking sticks
	}

	/**
	 * Has the philosopher release the chopsticks back into the cup
	 */
	public synchronized void release() {
		stickCount = stickCount + 2; //phil puts the 2 sticks back in the cup
	}

}