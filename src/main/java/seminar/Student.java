package seminar;

public class Student {
	private String _name;
	private String _surname;

	public Student(String name, String surname) {
		_name = name;
		_surname = surname;
	}

	public String getFullName() {
		return _name + " " + _surname;
	}

}
