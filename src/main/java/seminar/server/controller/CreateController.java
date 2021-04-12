package seminar.server.controller;

import seminar.Course;
import seminar.Location;
import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Response;
import seminar.server.repository.SeminarRepository;

public class CreateController implements Controller {
	private SeminarRepository _seminarRepository;

	public CreateController(SeminarRepository seminarRepository) {
		_seminarRepository = seminarRepository;
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
		String locationName = context.getRequest().getParameter("locationName")[0];
		String locationSeats = context.getRequest().getParameter("locationSeats")[0];
		_seminarRepository.save(
			Seminar
			.builder()
			.course(new Course(name, Integer.valueOf(number), description))
			.location(new Location(locationName, Integer.valueOf(locationSeats)))
			.build()
		);

		context.add(
			Response
			.builder()
			.redirect("/courses")
			.header("Location", "/courses")
			.build()
		);
	}

}
