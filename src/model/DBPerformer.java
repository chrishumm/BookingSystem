package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DBPerformer implements IPerformers {

	private static DBPerformer instance = null;


	public static DBPerformer getInstance() {
		if (instance == null)
			instance = new DBPerformer();
		return instance;
	}

	private Connection connection = null;


	public DBPerformer() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager
					.getConnection("jdbc:derby:BookingsDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Performer> getAllPerformers() {
		ArrayList<Performer> Performers = new ArrayList<Performer>();
		if (connection != null) {
			try {
				String sql = "SELECT * FROM Performer ORDER BY id";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					String desc = result.getString("desc");

					Performer Performer = new Performer(id, name, desc);
					Performers.add(Performer);

				}

				statement.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Performers;
	}


	@Override
	public void addPerformer(Performer c) {
		if (connection != null) {
			try {
				String sql = "INSERT INTO Performer(name, desc) VALUES ('"
						+ c.getName() + "', '" + c.getDesc() + "')";
				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	public void removePerformer(Performer c) {
		if (connection != null) {
			try {
				String sql = "DELETE FROM Performer " + "WHERE id = "
						+ c.getId();
				Statement statement = connection.createStatement();
				statement.executeUpdate(sql);
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
