package testing;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import dbadapter.Configuration;
import dbadapter.DBFacade;
import dbadapter.HolidayOffer;

/**
 * Testing our DBFacade.
 * 
 * @author swe.uni-due.de
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBFacade.class)
public class DBFacadeTest {

	/**
	 * Preparing classes with static methods
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(DriverManager.class);
	}

	@After
	public void tearDown() {

	}

	/**
	 * Testing getAvailableHolidayOffers with non-empty results.
	 */
	@Test
	public void testGetAvailableHolidayOffers() {
		// Declare necessary SQL queries
		String sqlSelect = "SELECT * FROM HolidayOffer WHERE starttime <= ? AND endtime >= ? AND capacity >= ?";
		String sqlSelectB = "SELECT * FROM Booking WHERE hid = ?";

		Timestamp stubStart = null;
		Timestamp stubEnd = null;

		// Set timestamps
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = dateFormat.parse("12/05/2015");
			long time = date.getTime();
			stubStart = new Timestamp(time);
			Date date2 = dateFormat.parse("12/20/2015");
			long time2 = date2.getTime();
			stubEnd = new Timestamp(time2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Mock return values
		PreparedStatement ps = mock(PreparedStatement.class);
		PreparedStatement psSelectB = mock(PreparedStatement.class);
		ResultSet rs = mock(ResultSet.class);
		ResultSet brs = mock(ResultSet.class);

		try {
			// Setting up return values for connection and statements
			Connection stubCon = mock(Connection.class);
			PowerMockito.when(
					DriverManager.getConnection(
							"jdbc:" + Configuration.getType() + "://"
									+ Configuration.getServer() + ":"
									+ Configuration.getPort() + "/"
									+ Configuration.getDatabase(),
							Configuration.getUser(),
							Configuration.getPassword())).thenReturn(stubCon);

			when(stubCon.prepareStatement(sqlSelect)).thenReturn(ps);
			when(stubCon.prepareStatement(sqlSelectB)).thenReturn(psSelectB);
			when(ps.executeQuery()).thenReturn(rs);
			when(psSelectB.executeQuery()).thenReturn(brs);

			// Setting up return values for methods
			when(rs.next()).thenReturn(true).thenReturn(false);
			when(rs.getInt(1)).thenReturn(0);
			when(rs.getTimestamp(2)).thenReturn(stubStart);
			when(rs.getTimestamp(3)).thenReturn(stubEnd);
			when(rs.getString(4)).thenReturn("Bahnstr. 6");
			when(rs.getString(5)).thenReturn("Duisburg");
			when(rs.getInt(6)).thenReturn(7);
			when(rs.getDouble(7)).thenReturn(4.5);

			// Setting up return values for corresponding bo
			when(brs.next()).thenReturn(true).thenReturn(false);
			when(brs.getInt(1)).thenReturn(0);
			when(brs.getTimestamp(2)).thenReturn(
					Timestamp.valueOf("2015-12-03 00:00:00"));
			when(brs.getTimestamp(3)).thenReturn(
					Timestamp.valueOf("2015-12-03 00:00:00"));
			when(brs.getTimestamp(4)).thenReturn(
					Timestamp.valueOf("2015-12-04 00:00:00"));
			when(brs.getBoolean(5)).thenReturn(true);
			when(brs.getString(6)).thenReturn("Peter Schulze");
			when(brs.getString(7)).thenReturn("peter@uni-due.de");
			when(brs.getDouble(8)).thenReturn(9.0);
			when(brs.getInt(9)).thenReturn(0);

			ArrayList<HolidayOffer> hos = DBFacade.getInstance()
					.getAvailableHolidayOffers(stubStart, stubEnd, 1);

			// Verify how often a method has been called
			verify(stubCon, times(1)).prepareStatement(sqlSelect);
			verify(stubCon, times(1)).prepareStatement(sqlSelectB);
			verify(ps, times(1)).executeQuery();
			verify(psSelectB, times(1)).executeQuery();

			// Verify return values
			assertTrue(hos.size() == 1);
			assertTrue(hos.get(0).getId() == 0);
			assertTrue(hos.get(0).getBookings().size() == 1);
			assertTrue(hos.get(0).getFee() == 4.5);
			// ...

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Testing getAvailableHolidayOffer with empty result.
	 */
	@Test
	public void testGetAvailableHolidayOffersEmpty() {

		// Declare necessary SQL queries
		String sqlSelect = "SELECT * FROM HolidayOffer WHERE starttime <= ? AND endtime >= ? AND capacity >= ?";
		String sqlSelectB = "SELECT * FROM Booking WHERE hid = ?";

		// Set timestamps
		Timestamp stubStart = null;
		Timestamp stubEnd = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = dateFormat.parse("12/05/2015");
			long time = date.getTime();
			stubStart = new Timestamp(time);
			Date date2 = dateFormat.parse("12/20/2015");
			long time2 = date2.getTime();
			stubEnd = new Timestamp(time2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create mock-objects
		Connection stubCon = mock(Connection.class);
		PreparedStatement ps = mock(PreparedStatement.class);
		PreparedStatement psSelectB = mock(PreparedStatement.class);
		ResultSet rs = mock(ResultSet.class);
		ResultSet brs = mock(ResultSet.class);

		try {
			PowerMockito.when(
					DriverManager.getConnection(
							"jdbc:" + Configuration.getType() + "://"
									+ Configuration.getServer() + ":"
									+ Configuration.getPort() + "/"
									+ Configuration.getDatabase(),
							Configuration.getUser(),
							Configuration.getPassword())).thenReturn(stubCon);
			// Setting up return values for connection and statements
			when(stubCon.prepareStatement(sqlSelect)).thenReturn(ps);
			when(stubCon.prepareStatement(sqlSelectB)).thenReturn(psSelectB);
			when(ps.executeQuery()).thenReturn(rs);
			when(psSelectB.executeQuery()).thenReturn(brs);

			// Setting up return values for methods of ho
			when(rs.next()).thenReturn(true).thenReturn(false);
			when(rs.getInt(1)).thenReturn(0);
			when(rs.getTimestamp(2)).thenReturn(stubStart);
			when(rs.getTimestamp(3)).thenReturn(stubEnd);
			when(rs.getString(4)).thenReturn("Bahnstr. 6");
			when(rs.getString(5)).thenReturn("Duisburg");
			when(rs.getInt(5)).thenReturn(7);
			when(rs.getDouble(7)).thenReturn(4.5);

			// Setting up return values for corresponding bo
			when(brs.next()).thenReturn(true).thenReturn(false);
			when(brs.getInt(1)).thenReturn(0);
			when(brs.getTimestamp(2)).thenReturn(
					Timestamp.valueOf("2015-12-03 00:00:00"));
			when(brs.getTimestamp(3)).thenReturn(
					Timestamp.valueOf("2015-12-07 00:00:00"));
			when(brs.getTimestamp(4)).thenReturn(
					Timestamp.valueOf("2015-12-13 00:00:00"));
			when(brs.getBoolean(5)).thenReturn(true);
			when(brs.getString(6)).thenReturn("Peter Schulze");
			when(brs.getString(7)).thenReturn("Duisburg");
			when(brs.getDouble(8)).thenReturn(9.0);
			when(brs.getInt(9)).thenReturn(0);

			ArrayList<HolidayOffer> hos = DBFacade.getInstance()
					.getAvailableHolidayOffers(stubStart, stubEnd, 1);

			// Verify how often a method has been called
			verify(stubCon, times(1)).prepareStatement(sqlSelect);
			verify(stubCon, times(1)).prepareStatement(sqlSelectB);
			verify(ps, times(1)).executeQuery();
			verify(psSelectB, times(1)).executeQuery();

			// Verify return values
			assertTrue(hos.size() == 0);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
