package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsCSVTest {
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void HTMLcourseHeader() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsCSV html = new SeminarDetailsCSV(_seminar);
		assertThat(html.header()).isEqualTo("1;Requirements Analysis;");
	}

	@Test
	public void HTMLcourseInfo() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsCSV html = new SeminarDetailsCSV(_seminar);
		assertThat(html.courseInfo()).isEqualTo("Identify and write scenarios;The main room;3\n");
	}

	@Test
	public void HTMLstudentLine() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsCSV html = new SeminarDetailsCSV(_seminar);
		assertThat(html.studentLine(StudentFactory.marioRossi().getFullName())).isEqualTo("Mario;Rossi\n");
	}

	@Test
	public void HTMLfooter() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsCSV html = new SeminarDetailsCSV(_seminar);
		assertThat(html.footer()).isEqualTo("");
	}

	@Test
	public void CSVcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetailsCSV(_seminar).render().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;3");
	}

	@Test
	public void CSVcourseWithOneStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetailsCSV(_seminar).render().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;2");
		assertThat(csv[1]).isEqualTo("Mario;Rossi");
	}

	@Test
	public void CSVcourseWithManyStudents() {
		_seminarBuilder
		.student(StudentFactory.marioRossi())
		.student(StudentFactory.marioBianchi());

		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetailsCSV(_seminar).render().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;1");
		assertThat(csv[2]).isEqualTo("Mario;Rossi");
		assertThat(csv[1]).isEqualTo("Mario;Bianchi");
	}
}
