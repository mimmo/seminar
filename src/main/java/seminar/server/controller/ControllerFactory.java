package seminar.server.controller;

import java.sql.Connection;
import java.util.List;

import seminar.importer.Importer;
import seminar.server.repository.SeminarRepository;

public class ControllerFactory {
	private Connection _connection;

	public ControllerFactory(Connection connection) {
		_connection = connection;
	}

	public List<Controller> create() {
		return List.of(
			new CreateController(new SeminarRepository(_connection)),
			new ImportController(() -> Importer.load("src/main/resources/seminars.csv"), new SeminarRepository(_connection)),
			new ListController(new SeminarRepository(_connection))
		);
	}
}
