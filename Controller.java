package eatingPackage;

/**
 * 
 * @author Cordell
 *
 *         class structure based on Orange
 */
public class Controller {
	private static final int NUM_PHILOSOPHERS = 5;
	public static final long PROCESSING_TIME = 20 * 1000;

	public static void main(String[] args) {
		Philosopher[] greatThinkers = new Philosopher[NUM_PHILOSOPHERS];
		Cup centralCup = new Cup();
		StarvingQueue starvationQueue = new StarvingQueue();
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			String name = "BLANK";
			switch(i) {
			case 0: name = "Cordell";
				break;
			case 1: name = "Lauren";
				break;
			case 2: name = "Andrew";
				break;
			case 3: name = "Nate";
				break;
			case 4: name = "Harper";
				break;	
			}
			greatThinkers[i] = new Philosopher(name, starvationQueue, centralCup);
		}

		// time delay method for the thinking and scheduling
		delay(PROCESSING_TIME, "Losing Consciousness");
		
		for(int i=0;i<NUM_PHILOSOPHERS;i++) {
			greatThinkers[i].stopPhilosophy();
		}
		
		for(int i=0;i<NUM_PHILOSOPHERS;i++) {
			greatThinkers[i].waitToStop();
		}
		
	}

	private static void delay(long time, String errMsg) {
		long sleepTime = Math.max(1, time);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(errMsg);
		}
	}
	
}
