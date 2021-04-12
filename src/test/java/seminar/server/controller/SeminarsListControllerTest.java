package seminar.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import _ch.gmtech.gmcommons.database.ConnectionFactory;
import seminar.Course;
import seminar.Location;
import seminar.Seminar;
import seminar.context.Context;
import seminar.server.repository.SeminarRepository;
import seminar.transformers.SeminarHTMLTransformer;

public class SeminarsListControllerTest {
	private Connection _connection;
	private ListController _seminarsListController;
	private SeminarRepository _seminarRepository;

	@Before
	public void setUp() throws SQLException {
		_connection = ConnectionFactory.h2InMemory("test", "test", "test").get();
		_seminarRepository = new SeminarRepository(_connection);
		_seminarsListController = new ListController(_seminarRepository);
	}

	@Test
	public void routeTest() {
		assertThat(_seminarsListController.handles("/courses"));
	}

	@Test
	public void listWithoutCourses() throws SQLException {
		Context context = new Context();

		_seminarsListController.execute(context);

		assertThat(_seminarRepository.size()).isEqualTo(0);
		assertThat(context.getResponse().getContentType()).isEqualTo("text/html");
		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
		assertThat(context.getResponse().getPayload()).isEqualTo("");
	}

	@Test
	public void listWithCourses() throws SQLException {
		Seminar seminar = Seminar.builder().course(new Course("name", 1, "description")).location(new Location("The hidden room", 10)).build();
		_seminarRepository.save(seminar);
		Context context = new Context();

		_seminarsListController.execute(context);

		assertThat(_seminarRepository.size()).isEqualTo(1);
		assertThat(context.getResponse().getPayload()).isEqualTo(new SeminarHTMLTransformer().apply(seminar));
	}

}
