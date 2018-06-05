package timer;

import application.VRApplication;

/**
 * Timer class to call the method checkPayment in the application. Main method
 * can be executed in a scheduled way.
 * 
 * @author swe.uni-due.de
 *
 */
public class Timer {

	public static void main(String[] args) {
		VRApplication vrApp = new VRApplication();
		vrApp.checkPayment();
		System.out
				.println("All bookings not paid and older than 14 days are successfully deleted");
	}
}
