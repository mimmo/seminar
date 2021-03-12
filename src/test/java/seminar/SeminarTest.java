package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
	public void seatsLeftWithoutParticipants() {
		assertThat(_seminar.getSeatsLeft()).isEqualTo(50);
	}

	@Test
	public void seatsLeftWithOneParticipants() {
		_seminar.addStudent(_student);
		assertThat(_seminar.getSeatsLeft()).isEqualTo(49);
	}

	@Test
	public void seatsLeftWhenFullyBooked() {
		_location = new Location("The main room", 3);
		_seminar = new Seminar(_location, _course);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		assertThat(_seminar.getSeatsLeft()).isEqualTo(0);
	}

	@Test
	public void addMoreStudentsThanSeats() {
		_location = new Location("The main room", 3);
		_seminar = new Seminar(_location, _course);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		assertThat(_seminar.addStudent(_student)).isFalse();
	}

	@Test
	public void theStudentsListContainsStudentsNameAndSurname() {
		_seminar.addStudent(_student);
		_seminar.addStudent(_student);
		assertThat(_seminar.getStudentsList()).isEqualTo(List.of("Mario Rossi", "Mario Rossi"));
	}

}
