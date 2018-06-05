package datatypes;

/**
 * Contains address informations about a holiday offer.
 * 
 * @author swe.uni-due.de
 *
 */
public class AddressData {

	private String street;
	private String town;

	public AddressData(String street, String town) {
		this.street = street;
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public String getTown() {
		return town;
	}

}
