package seminar;

public class Course {
	private String _description;
	private String _name;
	private int _number;

	public Course(String name, int number, String description) {
		_description = description;
		_name = name;
		_number = number;
	}

	public String getName() {
		return _name;
	}

    public int getNumber() {
    	return _number;
    }

    public String getDescription() {
    	return _description;
    }
}
