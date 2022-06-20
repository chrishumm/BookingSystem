package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class DBEvent implements IEventsList {

	private static DBEvent instance = null;


	public static DBEvent getInstance() {
		if (instance == null)
			instance = new DBEvent();
		return instance;
	}

	private Connection connection = null;

	public DBEvent() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager.getConnection("jdbc:derby:BookingsDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		if (connection != null) {
			try {
				String sql = "SELECT * FROM Event ORDER BY date";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					int price = result.getInt("price");
					String description = result.getString("description");
					int minAge = result.getInt("minAge");
					Timestamp date = result.getTimestamp("date");
					int seats = result.getInt("seats");

					ArrayList<Performer> Performers = new ArrayList<Performer>();

					Event event = new Event(id, name, price, description,
							minAge, date, seats, Performers);
					events.add(event);

				}

				sql = "SELECT * FROM Performer join PerformerBooking on Performer.id=PerformerBooking.PerformerId";
				result = statement.executeQuery(sql);

				while (result.next()) {
					int eventId = result.getInt("eventId");
					int PerformerId = result.getInt("PerformerId");
					String PerformerName = result.getString("name");
					String PerformerDesc = result.getString("description");

					events.get(eventId - 1)
							.addPerformer(
									new Performer(PerformerId, PerformerName,
											PerformerDesc));

				}

				statement.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return events;
	}


	@Override
	public void addEvent(Event event) {
		if (connection != null) {
			try {
				String sql = "INSERT INTO Event(name, prize,description,minAge,date,seats) VALUES ('"
						+ event.getName()
						+ "', '"
						+ event.getPrice()
						+ "', '"
						+ event.getDesc()
						+ "', '"
						+ event.getMinAge()
						+ "', '"
						+ event.getDate() + "', '" + event.getSeats() + "')";
				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	public void removeEvent(Event event) {

		{
			try {
				String sql = "DELETE FROM Event " + "WHERE id = "
						+ event.getId();
				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
