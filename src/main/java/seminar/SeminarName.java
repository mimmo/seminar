package seminar;

public class SeminarName {
	private String _name;
	private int _number;

	public SeminarName(String name, int number) {
		_name = name;
		_number = number;
	}

	@Override
	public String toString() {
		return _name + "#" + _number;
	}
}
