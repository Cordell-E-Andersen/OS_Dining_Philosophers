package eatingPackage;

import java.security.SecureRandom;

/**
 * 
 * @author Cordell
 *
 */
public class Philosopher implements Runnable {

	private volatile boolean inSymposium;
	private Thread philoThread;
	private SecureRandom rand;
	private StarvingQueue starvingPhilosophers;
	private Cup theCup;
	private int failCount;
	private int starving;
	private boolean isStarving;
	private int foodcount;

	public Philosopher(String philosopherId, StarvingQueue starvingPhilosophers, Cup theCup) {	
		this.theCup = theCup;
		this.starvingPhilosophers = starvingPhilosophers;
		philoThread = new Thread(this, philosopherId);
		inSymposium = true;
		rand = new SecureRandom();
		failCount = 0;
		foodcount = 1;
		starving = 5;
		isStarving = false;
		philoThread.start();

	}


	public void stopPhilosophy() {
		inSymposium = false;
		System.out.println(philoThread.getName() + " has drank " + foodcount + " times. They will shortly die of hemlock poisoning.");
	}

	public void waitToStop() {
		try {
			philoThread.join();
		} catch (InterruptedException ie) {
			System.err.println(philoThread.getName() + " has decided they are not suicidal.");
		}
	}

	public void run() {
		while (inSymposium) {
			if (starvingPhilosophers.countPhilosophers() == 0||starvingPhilosophers.getFirst().equals(this)) {
				System.out.println(philoThread.getName()+" attempts to embrace tranquility.");				
				if (theCup.acquire()) {
					System.out.println(philoThread.getName()+" should have grabbed two shot glasses.");
					eat();
					theCup.release();
					System.out.println(philoThread.getName()+" should have returned two shot glasses.");
					failCount = 0;
					if (isStarving) {
						starvingPhilosophers.takeOutPhilosopher();
						isStarving = false;
					}
				} else {
					System.out.println(philoThread.getName()+" failed to drink. SAD!");
					failCount++;
					if (failCount == starving) {
						System.out.println(philoThread.getName()+" IS LIVING!");
						starvingPhilosophers.addPhilosopher(this);
						isStarving = true;
					}
				}

			}
			philosophize();
		}

	}
	
	private void eat() {	
		long eatingTime = 250;
		try {
			Thread.sleep(eatingTime);
			foodcount++;
		} catch (Exception e) {
		}
		System.out.println(philoThread.getName() + " is drinking to their death. "+ "They have drank " + foodcount + " times of the hemlock basin.");
	}

	private void philosophize() {
		int thinkingTime = Math.abs(rand.nextInt(250) * 10);// rand.nextLong();
		System.out.println(philoThread.getName() + " is slowly noticing delirium from their self poisoning.");
		try {
			Thread.sleep(thinkingTime);
		} catch (Exception e) {
		}
	}
	
	private int getfoodcount() {
		return foodcount;
	}
}
