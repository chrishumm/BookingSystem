package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class DBBookings implements IBooking {

	private static DBBookings instance = null;

	/**
	 * static method to create an instance of this class
	 */
	public static DBBookings getInstance() {
		if (instance == null)
			instance = new DBBookings();
		return instance;
	}

	private Connection connection = null;

	/**
	 * The constructor to create an object. The constructor establishes a
	 * connection to the database.
	 */
	public DBBookings() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager
					.getConnection("jdbc:derby:BookingsDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to retrieve every booking for a specific event.
	 * 
	 * @param event
	 *            The event the bookings are supposed to be retrieved for
	 * @return ArrayList<Booking> A List of all the bookings as objects of the
	 *         type Booking
	 */
	@Override
	public ArrayList<Booking> getBookingsByEvent(Event event) {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		if (connection != null) {
			try {
				String sql = "SELECT * FROM CustomerBooking WHERE eventId="
						+ event.getId();
				System.out.println(sql);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					int id = result.getInt("bookingId");
					Timestamp bookingTime = result.getTimestamp("bookingTime");
					int numSeats = result.getInt("numberSeats");
					String customerName = result.getString("customerName");

					Booking booking = new Booking(id, event, bookingTime,
							numSeats, customerName);
					bookings.add(booking);

				}

				statement.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookings;
	}


	@Override
	public void addBooking(Event event, int numSeats, String customerName) {

		if (connection != null) {
			try {
				// get the current time
				Calendar calendar = Calendar.getInstance();
				java.sql.Timestamp currentTime = new java.sql.Timestamp(
						calendar.getTime().getTime());

				String sql = "INSERT INTO CustomerBooking(eventId, bookingTime,numberSeats,customerName) VALUES("
						+ event.getId()
						+ ", '"
						+ currentTime
						+ "', "
						+ numSeats + ", '" + customerName + "')";
				System.out.println(sql);

				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void removeBooking(Booking b) {
		if (connection != null) {
			try {
				String sql = "DELETE FROM CustomerBooking "
						+ "WHERE bookingId = " + b.getId();
				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();

				// TODO: UPDATE EVENT SEATS

				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
