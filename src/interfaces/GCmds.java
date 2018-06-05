package interfaces;

import java.util.ArrayList;

import datatypes.GuestData;
import dbadapter.Booking;
import dbadapter.HolidayOffer;

/**
 * Interface that provides all method to interact with a guest.
 * 
 * @author swe.uni-due.de
 *
 */
public interface GCmds {

	public ArrayList<HolidayOffer> getAvailableHolidayOffers(String arrivalTime, String departureTime, String persons);

	public Booking makeBooking(String arrivalTime, String departureTime, String hid, GuestData guestData,
			String persons);

}
