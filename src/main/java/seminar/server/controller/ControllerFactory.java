package seminar.server.controller;

import java.util.List;

import seminar.Seminar;

public class ControllerFactory {

	private List<Seminar> _seminars;

	public ControllerFactory(List<Seminar> seminars) {
		_seminars = seminars;
	}

	public List<Controller> create() {
		return List.of(
			new HTMLController(_seminars),
			new CSVController(_seminars),
			new RAWController(_seminars)
		);
	}
}
