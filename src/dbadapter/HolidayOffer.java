package dbadapter;

import java.sql.Timestamp;
import java.util.ArrayList;

import datatypes.AddressData;

/**
 * Class representing an offer
 * 
 * @author swe.uni-due.de
 *
 */
public class HolidayOffer {

	private int id;
	private Timestamp startTime;
	private Timestamp endTime;
	private AddressData addressData;
	private int capacity;
	private double fee;
	private ArrayList<Booking> bookings;

	public HolidayOffer(int id, Timestamp startTime, Timestamp endTime, AddressData addressData, int capacity,
			double fee) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.addressData = addressData;
		this.capacity = capacity;
		this.fee = fee;
		this.bookings = new ArrayList<Booking>();
	}

	public String toString() {
		return "HolidayOffer " + id + " startTime: " + startTime + " endTime: " + endTime + " capacity: " + capacity
				+ " fee: " + fee;
	}

	public int getId() {
		return id;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public AddressData getAddressData() {
		return addressData;
	}

	public int getCapacity() {
		return capacity;
	}

	public double getFee() {
		return fee;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	/**
	 * Checking if this offer is available. All bookings for this offer are
	 * iteratively checked.
	 * 
	 * @param arrivalTime
	 * @param departureTime
	 * @return
	 */
	public boolean available(Timestamp arrivalTime, Timestamp departureTime) {
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).overlap(arrivalTime, departureTime)) {
				return false;
			}
		}
		return true;
	}
}
