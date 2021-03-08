package seminar;

import java.util.ArrayList;
import java.util.List;

public class Seminar {
	private Course _course;
	private Location _location;
	private List<Student> _students;


	public Seminar(Location location, List<Student> students) {
		_students = students;
		_location = location;
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
		List<String> students = new ArrayList<>();
		for(Student student: _students) {
			students.add(student.getFullName());
		}

		return students;
	}}
