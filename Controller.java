package eatingPackage;

/**
 * 
 * @author Cordell: Main controller class where the philosophers are created and
 *         the program begins, class structure based on Orange
 */
public class Controller {
	private static final int NUM_PHILOSOPHERS = 5; // number of philosophers to be created
	public static final long PROCESSING_TIME = 20 * 1000; // time spent in session

	/**
	 * Creates the philosophers, cup, starving queue, and calls the methods to eat
	 * and not to eat (philosophize)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Philosopher[] greatThinkers = new Philosopher[NUM_PHILOSOPHERS];// creates phil array
		Cup centralCup = new Cup(); // creates cup
		StarvingQueue starvationQueue = new StarvingQueue(); //creates starvation queue
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) { //names of phils so we can track who is eating and philosophizing
			String name = "BLANK";
			switch (i) {
			case 0:
				name = "Cordell";
				break;
			case 1:
				name = "Lauren";
				break;
			case 2:
				name = "Andrew";
				break;
			case 3:
				name = "Nate";
				break;
			case 4:
				name = "Harper";
				break;
			}
			greatThinkers[i] = new Philosopher(name, starvationQueue, centralCup);//puts the phils in an array
		}

		// time delay method for the thinking and scheduling
		delay(PROCESSING_TIME, "Losing Consciousness");

		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			greatThinkers[i].stopPhilosophy();
		}

		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			greatThinkers[i].waitToStop();
		}
		
		for(int i=0; i<NUM_PHILOSOPHERS;i++) {
			System.out.println(greatThinkers[i].getFoodCount()+" times."); //final stats of how many times each phil ate
		}

	}

	/**
	 * Time they spend in session
	 * 
	 * @param time
	 * @param errMsg
	 */
	private static void delay(long time, String errMsg) {
		long sleepTime = Math.max(1, time);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(errMsg);
		}
	}

}
