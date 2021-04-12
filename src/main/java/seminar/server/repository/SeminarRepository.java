package seminar.server.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import seminar.Course;
import seminar.Location;
import seminar.Seminar;

public class SeminarRepository {

	private Connection _connection;

	public SeminarRepository(Connection connection) {
		_connection = connection;

		Statement createStatement;
		try {
			createStatement = connection.createStatement();
			createStatement.execute("CREATE TABLE IF NOT EXISTS seminars ("
				+ "Number int, "
				+ "Name varchar(255), "
				+ "Description varchar(255), "
				+ "Location varchar(255), "
				+ "Seats int"
				+ ");"
				);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void save(Seminar seminar) {
		save(List.of(seminar));
	}

	public void save(List<Seminar> seminars) {
		try (PreparedStatement st = _connection.prepareStatement("INSERT INTO seminars VALUES (?,?,?,?,?)")) {
			for(Seminar s: seminars) {
				st.setInt(1, s.getCourse().getNumber());
				st.setString(2, s.getCourse().getName());
				st.setString(3, s.getCourse().getDescription());
				st.setString(4, s.getLocation().getName());
				st.setInt(5, s.getLocation().getSeats());

				st.executeUpdate();
			}
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				_connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
     	}

	}

	public Seminar getFirst() {
		try {
			ResultSet rs = execute("SELECT * FROM seminars LIMIT 1");
			rs.next();
			return  Seminar
				.builder()
				.course(new Course(rs.getString("Name"), rs.getInt("Number"), rs.getString("Description")))
				.location(new Location(rs.getString("Location"), rs.getInt("Seats"))).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return  Seminar.builder().build();
		}
	}

	public List<Seminar> getAll() {
		List<Seminar> seminars = new ArrayList<>();
		try {
			ResultSet rs = execute("SELECT * FROM seminars");
			while (rs.next()) {
				seminars.add(
					Seminar
					.builder()
					.course(new Course(rs.getString("Name"), rs.getInt("Number"), rs.getString("Description")))
					.location(new Location(rs.getString("Location"), rs.getInt("Seats"))).build()
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seminars;
	}

	public int size() throws SQLException {
		ResultSet rs = execute("SELECT COUNT(*) as size FROM seminars");
		rs.next();

		return rs.getInt("size");
	}

	private ResultSet execute(String query) throws SQLException {
		Statement st = _connection.createStatement();
		ResultSet rs = st.executeQuery(query);

		return rs;
	}
}
