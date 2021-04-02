package seminar.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Response;
import seminar.transformers.SeminarHTMLTransformer;

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
		String payload = _seminars
			.stream()
			.map(s -> new SeminarHTMLTransformer().apply(s))
			.collect(Collectors.joining(""));

		context.add(Response
			.builder()
			.contentType("text/html")
			.payload(payload)
			.build());
	}

}
