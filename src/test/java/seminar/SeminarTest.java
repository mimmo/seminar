package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SeminarTest {
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void courseWithoutStudents() {
		Seminar seminar = _seminarBuilder.build();
		assertThat(seminar.getSeatsLeft()).isEqualTo(3);
	}

	@Test
	public void courseWithOneStudent() {
		Seminar seminar = _seminarBuilder
			.student(StudentFactory.marioRossi())
			.build();
		assertThat(seminar.getSeatsLeft()).isEqualTo(2);
	}

	@Test
	public void fullCourse() {
		Seminar seminar = SeminarBuilderFactory.create()
			.student(StudentFactory.marioRossi())
			.student(StudentFactory.marioBianchi())
			.student(StudentFactory.paoloVerdi())
			.build();
		assertThat(seminar.getSeatsLeft()).isEqualTo(0);
	}

	@Test
	public void overbooking() {
		Seminar seminar = SeminarBuilderFactory.create()
			.student(StudentFactory.marioRossi())
			.student(StudentFactory.marioBianchi())
			.student(StudentFactory.paoloVerdi())
			.student(StudentFactory.giovanniNeri())
			.build();
		assertThat(seminar.getSeatsLeft()).isEqualTo(0);
	}

	@Test
	public void doubleRegistration() {
		Student student = StudentFactory.marioRossi();
		Seminar seminar = SeminarBuilderFactory.create()
			.student(student)
			.student(student)
			.build();
		assertThat(seminar.getSeatsLeft()).isEqualTo(2);
	}
}
