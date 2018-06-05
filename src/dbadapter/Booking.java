package dbadapter;

import java.sql.Timestamp;

import datatypes.GuestData;

/**
 * Class representing a booking
 * 
 * @author swe.uni-due.de
 *
 */
public class Booking {

	int id;
	Timestamp creationDate;
	Timestamp arrivalTime;
	Timestamp departureTime;
	boolean paid;
	GuestData guestData;
	double price;
	int hid;

	public Booking(int id, Timestamp creationDate, Timestamp arrivalTime, Timestamp departureTime, boolean paid,
			GuestData guestData, double price, int hid) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.paid = paid;
		this.guestData = guestData;
		this.price = price;
		this.hid = hid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public GuestData getGuestData() {
		return guestData;
	}

	public void setGuestData(GuestData guestData) {
		this.guestData = guestData;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Checks if this booking overlaps with the given timespace.
	 * 
	 * @param arrivalTime
	 * @param departureTime
	 * @return
	 */
	public boolean overlap(Timestamp arrivalTime, Timestamp departureTime) {
		if ((arrivalTime.after(this.arrivalTime) || arrivalTime.equals(this.arrivalTime))
				&& (arrivalTime.before(this.departureTime) || arrivalTime.equals(this.departureTime))) {
			return true;
		}
		if ((departureTime.after(this.arrivalTime) || departureTime.equals(this.arrivalTime))
				&& (departureTime.before(this.departureTime) || departureTime.equals(this.departureTime))) {
			return true;
		}
		if (arrivalTime.before(this.arrivalTime) && departureTime.after(this.departureTime)) {
			return true;
		}
		return false;
	}

}
