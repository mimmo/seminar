package seminar;

import java.util.ArrayList;
import java.util.List;

public class Seminar {
	private String _location;
	private int _seatsLeft;

	private Course course;
	private Enrollment[] enrollments;

	public String getDescritpion() {
		return course.getDescription();
	}

	public String getLocation() {
    	return _location;
    }

	public int getSeatsLeft() {
		return _seatsLeft;
	}

	public SeminarName getName() {
		String name = course.getName();
		int number = course.getNumber();

		return new SeminarName(name, number);
	}

	public List<String> getStudentsList() {
		List<String> list = new ArrayList<String>();
		for(Enrollment e: enrollments) {
			list.add(e.getInfo());
		}

		return list;
	}}
