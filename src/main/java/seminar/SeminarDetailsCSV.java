package seminar;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.internal.StringUtil;

public class SeminarDetailsCSV implements SeminarDetails {
	 private Seminar _seminar;

	 public SeminarDetailsCSV(Seminar seminar) {
	 _seminar = seminar;
	 }

	@Override
	public String render() {
		 String csv = Stream.of(
		 String.valueOf(_seminar.getCourse().getNumber()),
		 _seminar.getCourse().getName(),
		 _seminar.getCourse().getDescription(),
		 _seminar.getLocation().getName(),
		 String.valueOf(_seminar.getSeatsLeft()) + "\n"
		 ).collect(Collectors.joining(";"));

		 csv += _seminar.getStudentsList()
		 .stream()
		 .map( s -> StringUtil.join(s.split(" "), ";"))
		 .collect(Collectors.joining("\n"));

		 return csv;
	}

}
