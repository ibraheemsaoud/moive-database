package testing;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import datatypes.GuestData;
import dbadapter.Booking;



public class BookingTest {
	Booking b;
	
	@Before
	public void setUp() throws Exception {
		b = new Booking(1, 
		  Timestamp.valueOf("2008-12-01 00:00:00"),
		  Timestamp.valueOf("2008-12-01 00:00:00"),
		  Timestamp.valueOf("2008-12-07 00:00:00"),
		  true, new GuestData("Müller","m@uni-due.de"),20, 2);
	}

	@Test
	public final void testOverlap() {
	      // The arrivalTime of b equals the tested arrivalTime,
	      // the departureTime of b equals the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-07 10:00:00")));

	      // The arrivalTime of b equals the tested arrivalTime,
	      // the departureTime of b is after the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-02 10:00:00")));

	      // The arrivalTime of b equals the tested arrivalTime,
	      // the departureTime of b is before the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-08 10:00:00")));

	      // The arrivalTime of b is after the 
	      // tested arrivalTime,
	      // the departureTime of b equals the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-11-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-07 10:00:00")));

	      // The arrivalTime of b is before the 
	      // tested arrivalTime,
	      // the departureTime of b equals the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-02 10:00:00"),
	    		  Timestamp.valueOf("2008-12-07 10:00:00")));

	      // The arrivalTime of b is after the 
	      // tested arrivalTime,
	      // the departureTime of b is after the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-11-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-02 10:00:00")));

	      // The arrivalTime of b is before the 
	      // tested arrivalTime,
	      // the departureTime of b is before the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-04 10:00:00"),
	    		  Timestamp.valueOf("2008-12-08 10:00:00")));

	      // The arrivalTime of b is after the 
	      // tested arrivalTime,
	      // the departureTime of b is before the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-11-01 10:00:00"),
	    		  Timestamp.valueOf("2009-01-01 10:00:00")));

	      // The arrivalTime of b is before the 
	      // tested arrivalTime,
	      // the departureTime of b is after the 
	      // tested departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2008-12-02 10:00:00"),
	    		  Timestamp.valueOf("2008-12-04 10:00:00")));

	      // The time period of b is before the 
	      // tested time period
	      assertEquals(false, b.overlap(
	    		  Timestamp.valueOf("2009-01-01 10:00:00"),
	    		  Timestamp.valueOf("2009-01-04 10:00:00")));

	      // The time period of b is after the 
	      // tested time period
	      assertEquals(false, b.overlap(
	    		  Timestamp.valueOf("2007-12-01 10:00:00"),
	    		  Timestamp.valueOf("2007-12-02 10:00:00")));

	      // The arrivalTime is greater than the departureTime
	      assertEquals(true, b.overlap(
	    		  Timestamp.valueOf("2009-12-01 10:00:00"),
	    		  Timestamp.valueOf("2008-12-02 10:00:00")));
	}
}
