package eatingPackage;
import java.security.SecureRandom;

/**
 * 
 * @author Cordell
 *
 */
public class Philosopher implements Runnable{
//	public enum State {//I now question whether or not we'll even need this...
//        Thinking,
//        Hungry,
//        Eating;
//
//        private static final int finalIndex = State.values().length - 1;
//       
//        State()//  I don't think this will be necessary... 
//        {
//            
//        }
//
//        State getNext() 
//        {
//            int currIndex = this.ordinal();
//            if (currIndex > finalIndex) 
//            {
//                currIndex = -1;
//            }
//            return State.values()[currIndex + 1];
//        }
//    }

	private volatile boolean inSymposium;
	private Thread philoThread;
 //   private State state;
    private SecureRandom rand;
    private StarvingQueue starvingPhilosophers;
    private ChopStick[] pileOSticks;

    public Philosopher(String philosopherId, StarvingQueue starvingPhilosophers, ChopStick[] pileOSticks) 
    {
//        state = State.Thinking;
    	this.pileOSticks = pileOSticks;
    	this.starvingPhilosophers=starvingPhilosophers;
        philoThread = new Thread(this, "Philosopher_"+philosopherId);
        inSymposium = true;
        philoThread.start();
    }

//    public State getState() 
//    {
//        return state;
//    }

    public void stopPhilosophy() {
    	inSymposium = false;
    }
    
    public void waitToStop() {
    	try {
    		philoThread.join();
    	}catch(InterruptedException ie) {
    		System.err.println(philoThread.getName()+" suffered a stroke.");
    	}
    }
    
    public void run() 
    {
    	while(inSymposium) {
    		if(starvingPhilosophers.countPhilosophers()==0) {//attempt to get sticks succeeds
    			eat();
    			//return sticks
    			//notify?
    		}
    		philosophize();
    	}
        // Stuff for what state it's in....?
        
    }
    
    private void eat() {
    	long eatingTime = 100;
    	System.out.println("Philosopher "+philoThread.getName()+" is eating.");
    	try {
    		Thread.sleep(eatingTime);
    	}catch(Exception e) {}    	
    }
    
    private void philosophize() {
    	long thinkingTime = Math.abs(rand.nextInt(250)*10);//rand.nextLong();
    	System.out.println("Philosopher "+philoThread.getName()+" is thinking.");
    	try {
    		Thread.sleep(thinkingTime);
    	}catch(Exception e) {}
    }
}
