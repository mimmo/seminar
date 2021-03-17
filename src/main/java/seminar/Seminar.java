package seminar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Seminar {
	private Course _course;
	private Location _location;
	private Set<Student> _students;

	public static Seminar.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Course course;
		private Location location;
		private Set<Student> students;

		public Seminar build() {
			Seminar result = new Seminar();
			result._course = course;
			result._location = location;
			result._students = students;
			return result;
		}

		public Builder course(Course course) {
			this.course = course;
			return this;
		}

		public Builder location(Location location) {
			this.location = location;
			return this;
		}

		public Builder student(Student student) {
			this.students.add(student);
			return this;
		}
		public Builder students(Set<Student> students) {
			this.students = students
				.stream()
				.collect(Collectors.toSet());
			return this;
		}

		private Builder() {
			course = new Course("", 0, "");
			location = new Location("", 0);
			students = new HashSet<>();
		}

	}

	public Course getCourse() {
		return _course;
	}

	public String getDescritpion() {
		return getCourse().getDescription();
	}

	public SeminarName getName() {
		String name = getCourse().getName();
		int number = getCourse().getNumber();

		return new SeminarName(name, number);
	}

	public Location getLocation() {
		return _location;
	}

	public int getSeatsLeft() {
		return Math.max(0, _location.getSeats() - _students.size());
	}

	public List<String> getStudentsList() {
		return _students.stream().map(s -> s.getFullName()).collect(Collectors.toList());
	}

	private Seminar() {}
}
