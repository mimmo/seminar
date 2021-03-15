package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SeminarTest {
	private Course _course;
	private Location _location;
	private Seminar _seminar;
	private Student _student;

	@Before
	public void setUp() {
		_course = new Course("Requirements Analysis", 1, "Identify and write scenarios");
		_location = new Location("The main room", 50);
		_student = new Student("Mario", "Rossi");
		_seminar = new Seminar(_location, _course);
	}

	@Test
	public void courseWithoutStudents() {
		assertThat(_seminar.getSeatsLeft()).isEqualTo(50);
	}

	@Test
	public void courseWithOneStudent() {
		_seminar.addStudent(_student);
		assertThat(_seminar.getSeatsLeft()).isEqualTo(49);
	}

	@Test
	public void fullCourse() {
		_location = new Location("The main room", 3);
		_seminar = new Seminar(_location, _course);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		assertThat(_seminar.getSeatsLeft()).isEqualTo(0);
	}

	@Test
	public void overbooking() {
		_location = new Location("The main room", 3);
		_seminar = new Seminar(_location, _course);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		assertThat(_seminar.getSeatsLeft()).isEqualTo(0);
	}
}
