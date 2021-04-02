package seminar.server.controller;

import java.util.List;

import seminar.Course;
import seminar.Seminar;

public class ControllerFactory {
	private List<Course> _coursePersistence;
	private List<Seminar> _seminars;

	public ControllerFactory(List<Course> coursePersistence, List<Seminar> seminars) {
		_coursePersistence = coursePersistence;
		_seminars = seminars;
	}

	public List<Controller> create() {
		return List.of(
			new HTMLController(_seminars),
			new CSVController(_seminars),
			new RAWController(_seminars),
			new CourseCreateController(_coursePersistence)
		);
	}
}
