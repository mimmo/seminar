package seminar.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Response;
import seminar.transformers.SeminarRAWTransformer;

public class RAWController implements Controller{
	private List<Seminar> _seminars;

	public RAWController(List<Seminar> seminars) {
		_seminars = seminars;
	}

	@Override
	public boolean handles(String route) {
		return "/course/raw".equals(route);
	}

	@Override
	public void execute(Context context) throws Exception {
		String payload = _seminars
			.stream()
			.map(s -> new SeminarRAWTransformer().apply(s))
			.collect(Collectors.joining(""));

		context.add(Response
			.builder()
			.payload(payload)
			.build());
	}
}
