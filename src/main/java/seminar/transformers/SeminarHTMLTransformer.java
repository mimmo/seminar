package seminar.transformers;

import java.util.List;

import seminar.Seminar;
import seminar.transformers.formats.HTMLFull;

public class SeminarHTMLTransformer {

	public String apply(Seminar seminar) {
		HTMLFull html = new HTMLFull();

		html.title(seminar.getName().toString());
		html.div(seminar.getName().toString());
		html.ul(List.of(
			seminar.getDescritpion(),
			seminar.getLocation().getName(),
			String.valueOf(seminar.getSeatsLeft()))
		);
		if(seminar.getStudentsList().size() > 0) {
			html.div("partecipanti");
			html.ul(seminar.getStudentsList());
		}

		return html.render();
	}
}
