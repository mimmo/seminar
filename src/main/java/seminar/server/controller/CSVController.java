package seminar.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import seminar.Seminar;
import seminar.SeminarDetailsCSV;
import seminar.server.servlet.Context;
import seminar.server.servlet.Response;

public class CSVController implements Controller {
	private List<Seminar> _seminars;

	public CSVController(List<Seminar> seminars) {
		_seminars = seminars;
	}

	@Override
	public boolean handles(String route) {
		return "/course/csv".equals(route);
	}

	@Override
	public void execute(Context context) throws Exception {
		String payload = _seminars
			.stream()
			.map(s -> new SeminarDetailsCSV(s).render())
			.collect(Collectors.joining(""));

		context.add(Response
			.builder()
			.contentType("text/csv")
			.header("Content-Disposition", "attachment; fileName=data.csv")
			.payload(payload)
			.build());
	}

}
