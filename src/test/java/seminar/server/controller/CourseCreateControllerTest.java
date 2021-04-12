package seminar.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import _ch.gmtech.gmcommons.database.ConnectionFactory;
import seminar.Course;
import seminar.Location;
import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Request;
import seminar.server.repository.SeminarRepository;


public class CourseCreateControllerTest {

	private Connection _connection;
	private CreateController _courseCreateController;
	private SeminarRepository _seminarRepository;

	@Before
	public void setUp() {
		_connection = ConnectionFactory.h2InMemory("test", "test", "test").get();
		_seminarRepository = new SeminarRepository(_connection);
		_courseCreateController = new CreateController(_seminarRepository);
	}

	@Test
	public void routeTest() {
		assertThat(_courseCreateController.handles("/course/create")).isTrue();
	}

	@Test
	public void ok() throws SQLException {
		Request request = new Request(Map.of(
			"name", new String[]{"Requirements Analysis"},
			"number", new String[] {"1"},
			"description", new String[] {"Identify and write scenarios"},
			"locationName", new String[] {"The main room"},
			"locationSeats", new String[] {"10"}
		));
		Context context = new Context(request);

		_courseCreateController.execute(context);

		assertThat(context.getResponse().getRedirectURL()).isEqualTo("/courses");
		assertThat(_seminarRepository.size()).isEqualTo(1);
		Seminar seminar = _seminarRepository.getFirst();
		assertThat(seminar.getCourse().getName()).isEqualTo("Requirements Analysis");
		assertThat(seminar.getCourse().getNumber()).isEqualTo(1);
		assertThat(seminar.getCourse().getDescription()).isEqualTo("Identify and write scenarios");
		assertThat(seminar.getLocation().getName()).isEqualTo("The main room");
		assertThat(seminar.getLocation().getSeats()).isEqualTo(10);
	}

	@Test
	public void createAnotherCourse() throws SQLException {
		_seminarRepository.save(Seminar.builder().course(new Course("name", 1, "description")).location(new Location("The hidden room", 10)).build());
		Request request = new Request(Map.of(
			"name", new String[]{"Requirements Analysis"},
			"number", new String[] {"1"},
			"description", new String[] {"Identify and write scenarios"},
			"locationName", new String[] {"The main room"},
			"locationSeats", new String[] {"10"}
		));
		Context context = new Context(request);

		_courseCreateController.execute(context);

		assertThat(_seminarRepository.size()).isEqualTo(2);
	}

}
