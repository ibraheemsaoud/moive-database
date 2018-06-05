package datatypes;

/**
 * Contains the necessary informations about a guest.
 * 
 * @author swe.uni-due.de
 *
 */
public class GuestData {
	private String name;
	private String email;

	public GuestData(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
