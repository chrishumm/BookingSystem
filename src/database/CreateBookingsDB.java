package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateBookingsDB {
	public static void main(String[] args) {
		try {
			// Connect to the database
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");			//
			String url = "jdbc:derby:BookingsDB;create=true";
			Connection connection = DriverManager.getConnection(url);
			System.out
					.println("Connecting to Database 'BookingsDB' successful.");
			//
			Statement statement = connection.createStatement();
			// Delete all tables
			String sql = "";


			sql = "CREATE TABLE Event ("
					+ "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
					+ "name VARCHAR(100) NOT NULL, "
					+ "price DECIMAL NOT NULL, "
					+ "description VARCHAR(500) NOT NULL, "
					+ "minAge INTEGER NOT NULL, " + "date TIMESTAMP NOT NULL, "
					+ "seats INT NOT NULL)";
			System.out.println(sql);
			//statement.executeUpdate(sql);
			// Create Bookings table
			sql = "CREATE TABLE CustomerBooking ("
					+ "eventId INTEGER NOT NULL, "
					+ "bookingId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
					+ "bookingTime TIMESTAMP NOT NULL, "
					+ "numberSeats INTEGER NOT NULL, "
					+ "customerName VARCHAR(100) NOT NULL)";
			System.out.println(sql);
			//statement.executeUpdate(sql);

			sql = "CREATE TABLE PerformerBooking ("
					+ "eventId INTEGER NOT NULL, "
					+ "performerId INTEGER NOT NULL, "
					+ "PRIMARY KEY(eventId,performerId))";
			System.out.println(sql);
			statement.executeUpdate(sql);

			// ADD DATA

			// Add events for testing
			sql = "INSERT INTO Event (name,price,description,minAge,date,seats) VALUES("
					+ "'SecondEvent',"
					+ "40,"
					+ "'This is the second event', "
					+ "18, " + "'2017-10-23 10:10:10.0', " + "250)";
			System.out.println(sql);
			statement.executeUpdate(sql);
			sql = "INSERT INTO Event (name,price,description,minAge,date,seats) VALUES("
					+ "'ThirdEvent',"
					+ "40,"
					+ "'This is the third event', "
					+ "18, " + "'2017-10-28 10:10:10.0', " + "250)";
			System.out.println(sql);
			statement.executeUpdate(sql);

			// Add placeholder Performers for testing
			sql = "INSERT INTO Performer (name,description) VALUES("
					+ "'Performer One'," + "'This is a mediocre Performer!')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			sql = "INSERT INTO Performer (name,description) VALUES("
					+ "'Performer Two'," + "'This is a mediocre Performer!')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			sql = "INSERT INTO Performer (name,description) VALUES("
					+ "'Performer Three'," + "'This is a mediocre Performer!') ";
			System.out.println(sql);
			statement.executeUpdate(sql);

			// Assign Performers to events by creating Performer booking test data
			sql = "INSERT INTO PerformerBooking (eventId,PerformerId) VALUES("
					+ "1," + "1) ";
			System.out.println(sql);
			statement.executeUpdate(sql);
			sql = "INSERT INTO PerformerBooking (eventId,PerformerId) VALUES("
					+ "2," + "2) ";
			System.out.println(sql);
			statement.executeUpdate(sql);
			sql = "INSERT INTO PerformerBooking (eventId,PerformerId) VALUES("
					+ "2," + "3) ";
			System.out.println(sql);
			statement.executeUpdate(sql);

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
