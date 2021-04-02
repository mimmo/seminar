package seminar.transformers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import seminar.Seminar;
import seminar.SeminarBuilderFactory;
import seminar.StudentFactory;

public class SeminarRAWTransformerTest {
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void RAWcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarRAWTransformer().apply(_seminar).split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements Analysis");
		assertThat(raw[2]).isEqualTo("Location: The main room");
		assertThat(raw[3]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[4]).isEqualTo("Seats left: 3");
	}

	@Test
	public void RAWcourseWithOneStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarRAWTransformer().apply(_seminar).split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements Analysis");
		assertThat(raw[2]).isEqualTo("Location: The main room");
		assertThat(raw[3]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[4]).isEqualTo("Seats left: 2");
		assertThat(raw[5]).isEqualTo("Mario Rossi");
	}

	@Test
	public void RAWcourseWithManyStudents() {
		_seminarBuilder
			.student(StudentFactory.marioRossi())
			.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarRAWTransformer().apply(_seminar).split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements Analysis");
		assertThat(raw[2]).isEqualTo("Location: The main room");
		assertThat(raw[3]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[4]).isEqualTo("Seats left: 1");
		assertThat(raw[5]).isEqualTo("Mario Rossi");
		assertThat(raw[6]).isEqualTo("Mario Bianchi");
	}
}
