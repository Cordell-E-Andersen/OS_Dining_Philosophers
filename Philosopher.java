package eatingPackage;

import java.security.SecureRandom;

/**
 * 
 * @author Cordell: Code referenced from Nate: Philosopher takes each person and
 *         allows them to attempt to grab chopsticks and when they fail to do so
 *         has them philosophize
 *
 */
public class Philosopher implements Runnable {
	private volatile boolean inSymposium; //in session or not
	private Thread philoThread;
	private SecureRandom rand;
	private StarvingQueue starvingPhilosophers; //queue to keep track of phils that aren't eating
	private Cup theCup; //the cup************************************
	private int failCount; //number of times a phil has failed to grab chopsticks
	private int starving; // determines if a phil is starving
	private boolean isStarving; // phil hasn't been able to get the sticks for x number of times and needs to eat
	private int foodcount; // number of times a phil has eaten total

	/**
	 * Creates the philosophers with use of an ID, the cup, and the variables for the starving queue
	 * 
	 * @param philosopherId
	 * @param starvingPhilosophers
	 * @param theCup
	 */
	public Philosopher(String philosopherId, StarvingQueue starvingPhilosophers, Cup theCup) {
		this.theCup = theCup;
		this.starvingPhilosophers = starvingPhilosophers; 
		philoThread = new Thread(this, philosopherId);
		inSymposium = true; // program is running/phils are in session
		rand = new SecureRandom(); // random amount of time they will philosophize for
		failCount = 0; // count of how many times they have failed to eat
		foodcount = 0; // count of how many times they have eaten
		starving = 5; // if they fail 5 times they are starving
		isStarving = false; //whether or not a particular phil is starving
		philoThread.start();// starts creating the phils and assigning them IDs

	}

	/**
	 * Stops philosophizing and begins the end of the program
	 */
	public void stopPhilosophy() {
		inSymposium = false; //session ends
	}

	/**
	 * Allows it to keep running while midway and then joins it back to the main
	 * thread
	 */
	public void waitToStop() {
		try {
			philoThread.join(); //joins back
		} catch (InterruptedException ie) {
			System.err.println(philoThread.getName() + " has failed to leave symmposium.");
		}
	}

	/**
	 * The philosophers attempt to grab sticks, if success they eat, if
	 * fail they philosophize and that philosopher gains a +1 on their starving
	 * count. If their count reaches a set amount, they gain priority to grab sticks
	 */
	public void run() {
		while (inSymposium) {
			if (starvingPhilosophers.countPhilosophers() == 0 || starvingPhilosophers.getFirst().equals(this)) { //gets starving phils first
				System.out.println(philoThread.getName() + " attempts to placate the existance of hunger.");
				if (theCup.acquire()) {
					System.out.println(philoThread.getName() + " should have grabbed two chop sticks."); //grabs two
					eat();
					theCup.release();
					System.out.println(philoThread.getName() + " should have returned two chop sticks."); //returns two
					failCount = 0; //phil ate so the fail count is reset
					if (isStarving) { //takes them out of starving since the failCount is reset
						starvingPhilosophers.takeOutPhilosopher();
						isStarving = false;
					}
				} else {
					System.out.println(philoThread.getName() + " failed to eat. SAD!"); //unable to grab chopsticks since there is less than 2
					failCount++; //adds to failCount
					if (failCount == starving) {// if failCount = starving the phil is moved to the starving queue
						System.out.println(philoThread.getName() + " is starving");
						starvingPhilosophers.addPhilosopher(this);
						isStarving = true;
					}
				}

			}
			philosophize(); //act of philosophizing while waiting for food
		}

	}

	/**
	 * Philosopher eats after getting the sticks
	 */
	private void eat() { //takes time to eat
		long eatingTime = 250;
		try {
			Thread.sleep(eatingTime);
			foodcount++; //adds to the # of times this phil has eaten
		} catch (Exception e) {
		}
		System.out.println(philoThread.getName() + " is eating.");
	}

	/**
	 * Philosopher philosophizes (sleeps) while waiting to eat
	 */
	private void philosophize() {//takes time to philosophize before trying to eat again
		int thinkingTime = Math.abs(rand.nextInt(250) * 10);
		System.out.println(philoThread.getName() + " is contemplating the meaning of rice.");
		try {
			Thread.sleep(thinkingTime);
		} catch (Exception e) {
		}
	}

	/**
	 * Tracks how many times each person has eaten
	 * 
	 * @return foodcount
	 */
	public int getFoodCount() {
		System.out.print(philoThread.getName()+" has eaten ");
		return foodcount;
	}
}
