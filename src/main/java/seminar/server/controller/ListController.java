package seminar.server.controller;

import java.util.stream.Collectors;

import seminar.context.Context;
import seminar.context.Response;
import seminar.server.repository.SeminarRepository;
import seminar.transformers.SeminarHTMLTransformer;

public class ListController implements Controller {
	private SeminarRepository _seminarRepository;

	public ListController(SeminarRepository seminarRepository) {
		_seminarRepository = seminarRepository;
	}

	@Override
	public boolean handles(String route) {
		return "/courses".equals(route);
	}

	@Override
	public void execute(Context context) {
		String payload = _seminarRepository.getAll()
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
