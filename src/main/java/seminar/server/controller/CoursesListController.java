package seminar.server.controller;

import java.util.List;

import seminar.Course;
import seminar.context.Context;
import seminar.context.Response;

public class CoursesListController {
	private List<Course> _coursePersistence;

	public CoursesListController(List<Course> coursePersistence) {
		_coursePersistence = coursePersistence;
	}

	public Object handles(String route) {
		return "/courses".equals(route);
	}

	public void execute(Context context) {
		context.add(Response
			.builder()
			.contentType("text/html")
			.build());
	}

}
