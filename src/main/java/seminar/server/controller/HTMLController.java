package seminar.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import seminar.Seminar;
import seminar.SeminarDetailsHTML;
import seminar.server.servlet.Context;
import seminar.server.servlet.Response;

public class HTMLController implements Controller{
	private List<Seminar> _seminars;

	public HTMLController(List<Seminar> seminars) {
		_seminars = seminars;
	}

	@Override
	public boolean handles(String route) {
		return "/course/html".equals(route);
	}

	@Override
	public void execute(Context context) throws Exception {
//		context.getRequest().getParameters()
		String payload = _seminars
			.stream()
			.map(s -> new SeminarDetailsHTML(s).render())
			.collect(Collectors.joining(""));

		context.add(Response
			.builder()
			.contentType("text/html")
			.payload(payload)
			.build());
	}

}
