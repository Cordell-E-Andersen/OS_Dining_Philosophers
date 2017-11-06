package eatingPackage;

/**
 * 
 * @author Cordell
 *
 *         class structure based on Orange
 */
public class Controller {
	private static final int NUM_PHILOSOPHERS = 1;
	private static final int NUM_STICKS = 5;

	public static void main(String[] args) {
		Philosopher[] greatThinkers = new Philosopher[NUM_PHILOSOPHERS];
		ChopStick[] pileOSticks = new ChopStick[NUM_STICKS];// or will this be an array of ChopSticks?
		StarvingQueue starvationQueue = new StarvingQueue();
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			greatThinkers[i] = new Philosopher("" + i + 1, starvationQueue, pileOSticks);
		}

		// time delay method for the thinking and scheduling

	}

}
