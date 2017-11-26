package eatingPackage;

public class Cup {
	private int stickCount;
	
	public Cup() {
		stickCount = 5;
	}
	
	public synchronized boolean acquire() {
		boolean grabbed = false;

		if(stickCount >=2) {
			stickCount=stickCount-2;
			grabbed=true;
		}
		
		return grabbed;
	}
	
	
	public synchronized void release() {
		stickCount = stickCount+2;
	}

	
}