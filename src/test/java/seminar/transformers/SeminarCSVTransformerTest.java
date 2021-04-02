package seminar.transformers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import seminar.Seminar;
import seminar.SeminarBuilderFactory;
import seminar.StudentFactory;

public class SeminarCSVTransformerTest {
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void CSVcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarCSVTransformer().apply(_seminar).split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;3;");
	}

	@Test
	public void CSVcourseWithOneStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarCSVTransformer().apply(_seminar).split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;2;");
		assertThat(csv[1]).isEqualTo("Mario;Rossi;");
	}

	@Test
	public void CSVcourseWithManyStudents() {
		_seminarBuilder
		.student(StudentFactory.marioRossi())
		.student(StudentFactory.marioBianchi());

		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarCSVTransformer().apply(_seminar).split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;1;");
		assertThat(csv[1]).isEqualTo("Mario;Rossi;");
		assertThat(csv[2]).isEqualTo("Mario;Bianchi;");
	}
}
