package seminar.server.controller;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import seminar.Course;
import seminar.Location;
import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Response;
import seminar.server.repository.SeminarRepository;

public class ImportController implements Controller {
	private SeminarRepository _seminarRepository;
	private Supplier<List<String>> _linesSupplier;

	public ImportController(Supplier<List<String>> linesSupplier, SeminarRepository seminarRepository) {
		_linesSupplier = linesSupplier;
		_seminarRepository = seminarRepository;
	}

	@Override
	public boolean handles(String route) {
		return "/courses/import".equals(route);
	}

	@Override
	public void execute(Context context) throws Exception {
		List<Seminar> seminars = _linesSupplier.get()
			.stream()
			.filter(l -> !l.isBlank())
			.map(l -> {
				String[] seminarData = l.split(";");
				return Seminar
					.builder()
					.course(new Course(seminarData[1], Integer.valueOf(seminarData[0]), seminarData[2]))
					.location(new Location(seminarData[3], Integer.valueOf(seminarData[4])))
					.build();
			})
			.collect(Collectors.toList());
		_seminarRepository.save(seminars);

		context.add(
			Response
			.builder()
			.redirect("/courses")
			.header("Location", "/courses")
			.build()
		);
	}

}
