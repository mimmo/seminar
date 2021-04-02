package seminar.transformers;

import seminar.Seminar;
import seminar.transformers.formats.CSV;
import seminar.transformers.formats.CSV.CsvItem;

public class SeminarCSVTransformer {

	public String apply(Seminar seminar) {
		CSV csv = new CSV()
			.row(
				CsvItem.of(seminar.getCourse().getNumber()),
				CsvItem.of(seminar.getCourse().getName()),
				CsvItem.of(seminar.getCourse().getDescription()),
				CsvItem.of(seminar.getLocation().getName()),
				CsvItem.of(seminar.getSeatsLeft()));

		seminar.getStudentsList().forEach(s -> csv.row(s.split(" ")));
		return csv.render();
	}
}
