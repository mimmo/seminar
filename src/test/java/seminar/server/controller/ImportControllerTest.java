package seminar.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import _ch.gmtech.gmcommons.database.ConnectionFactory;
import seminar.context.Context;
import seminar.server.repository.SeminarRepository;

public class ImportControllerTest {
	private Connection _connection;
	private ImportController _importController;
	private SeminarRepository _seminarRepository;
	private List<String> _lines;

	@Before
	public void setUp() throws SQLException {
		_connection = ConnectionFactory.h2InMemory("test", "test", "test").get();
		_seminarRepository = new SeminarRepository(_connection);
		_lines = new ArrayList<>();
		_importController = new ImportController(() -> _lines, _seminarRepository);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void routeTest() {
		assertThat(_importController.handles("/courses/import"));
	}

	@Test
	public void fileIsEmpty() throws Exception {
		Context context = new Context();
		_importController.execute(context);
		assertThat(_seminarRepository.size()).isEqualTo(0);
	}
	@Test
	public void fileWithOneLine() throws Exception {
		_lines.add("0;Requirements Analysis;Identify and write scenarios;The main room;8;");
		Context context = new Context();
		_importController.execute(context);
		assertThat(_seminarRepository.size()).isEqualTo(1);
	}
	@Test
	public void shouldSkipEmptyLines() throws Exception {
		_lines.add("");
		Context context = new Context();
		_importController.execute(context);
		assertThat(_seminarRepository.size()).isEqualTo(0);
	}
}
