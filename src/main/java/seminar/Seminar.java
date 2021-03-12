package seminar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Seminar {
	private Course _course;
	private Location _location;
	private List<Student> _students = new ArrayList<>();

	public Seminar(Location location, Course course) {
		_course = course;
		_location = location;
	}

	public boolean addStudent(Student student) {
		return (getSeatsLeft() > 0) ? _students.add(student) : false;
	}

	public String getDescritpion() {
		return _course.getDescription();
	}

	public SeminarName getName() {
		String name = _course.getName();
		int number = _course.getNumber();

		return new SeminarName(name, number);
	}

	public String getLocation() {
		return _location.getName();
	}

	public int getSeatsLeft() {
		return _location.getSeats() - _students.size();
	}

	public List<String> getStudentsList() {
		return _students.stream().map(s -> s.getFullName()).collect(Collectors.toList());
	}
}
