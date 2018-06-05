package dbadapter;

/**
 * This class is used to declare access data for the SQL server
 * 
 * @author swe.uni-due.de
 *
 */
public class Configuration {

	// Please enter the full-path to your database file here
	private static final String sqlitePath = "C:/Users/Roman/Desktop/vacationrental";

	// The following constants are only needed if you use MySQL instead of
	// SQLite
	// Replace these static values with your database configuation.
	private static final String SERVER = "localhost";
	private static final String TYPE = "mysql";
	private static final int PORT = 3306;
	private static final String DATABASE = "";
	private static final String USER = "";
	/**
	 * This password in stored in plaintext. We assume that only the owner can
	 * access this file and that all login data is tranfered in an encrypted way
	 * (e.g. SSL).
	 */
	private static final String PASSWORD = "1234";

	public static String getServer() {
		return SERVER;
	}

	public static String getType() {
		return TYPE;
	}

	public static int getPort() {
		return PORT;
	}

	public static String getDatabase() {
		return DATABASE;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getSqlitePath() {
		return sqlitePath;
	}

}
