package eatingPackage;
/**
 * 
 * @author Cordell
 *
 */
public class ChopStick {
	private boolean available = true;

	public synchronized void pickUp() {
		while (!available) {
			try {
				wait();
			} catch (Exception e) {

			}
		}
		available = false;
	}

	public synchronized void putDown() {
		try {
			available = true;
			notifyAll();
		} catch (Exception e) {

		}
	}
}
