package seminar.transformers;

import seminar.Seminar;
import seminar.transformers.formats.RAW;

public class SeminarRAWTransformer {

	public String apply(Seminar seminar) {
		RAW raw = new RAW();

		raw.line("Number: " + seminar.getCourse().getNumber());
		raw.line("Name: " + seminar.getCourse().getName());
		raw.line("Location: " + seminar.getLocation().getName());
		raw.line("Description: " + seminar.getDescritpion());
		raw.line("Seats left: " + seminar.getSeatsLeft());
		seminar.getStudentsList().forEach(s -> raw.line(s));

		return raw.render();
	}
}
