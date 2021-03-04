package seminar;

public class Student {
	private String _name;
	private String _surname;

	public String getInfo() {
		return getFullName();
	}

	private String getFullName() {
		return _name + " " + _surname;
	}

}
