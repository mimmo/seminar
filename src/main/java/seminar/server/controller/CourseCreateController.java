package seminar.server.controller;

import java.util.List;

import seminar.Course;
import seminar.context.Context;
import seminar.context.Response;

public class CourseCreateController implements Controller {
	private List<Course> _coursePersistence;

	public CourseCreateController(List<Course> coursePersistence) {
		_coursePersistence = coursePersistence;
	}

	@Override
	public boolean handles(String route) {
		return "/course/create".equals(route);
	}

	@Override
	public void execute(Context context) {
		String name = context.getRequest().getParameter("name")[0];
		String number = context.getRequest().getParameter("number")[0];
		String description = context.getRequest().getParameter("description")[0];
		_coursePersistence.add(new Course(name, Integer.valueOf(number), description));

		context.add(
			Response
			.builder()
			.redirect("/courses")
			.header("Location", "/courses")
			.build()
		);
	}

}
