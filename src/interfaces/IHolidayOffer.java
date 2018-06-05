package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;

import datatypes.GuestData;
import dbadapter.Booking;
import dbadapter.HolidayOffer;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author swe.uni-due.de
 *
 */
public interface IHolidayOffer {

	public ArrayList<HolidayOffer> getAvailableHolidayOffers(
			Timestamp arrivalTime, Timestamp departureTime, int persons);

	public Booking bookingHolidayOffer(Timestamp arrivalTime,
			Timestamp departureTime, int hid, GuestData guestData, int persons);

	public void setAvailableHolidayOffer();

}
