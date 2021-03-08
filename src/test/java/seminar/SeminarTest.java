package seminar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SeminarTest {

	@Test
	public void getTheStudentsList() {
		Location location = new Location("room1", 100);
		Student student = new Student("mario", "rossi");
		Seminar seminar = new Seminar(location, List.of(student));
		assertEquals(seminar.getStudentsList(), List.of("mario rossi"));
	}

	@Test
	public void getTheSeatsLeft() {
		Location location = new Location("room1", 100);
		Student student = new Student("mario", "rossi");
		Seminar seminar = new Seminar(location, List.of(student));
		assertEquals(seminar.getSeatsLeft(), 99);
	}

	@Test
	public void getTheLocationName() {
		Location location = new Location("room1", 100);
		Seminar seminar = new Seminar(location, List.of());
		assertEquals(seminar.getLocation(), "room1");
	}
}
