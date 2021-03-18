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
		_seminarBuilder.student(StudentFactory.marioRossi());
		_seminarBuilder.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetailsCSV(_seminar).render().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;1");
		assertThat(csv[1]).isEqualTo("Mario;Rossi");
		assertThat(csv[2]).isEqualTo("Mario;Bianchi");
	}
}
