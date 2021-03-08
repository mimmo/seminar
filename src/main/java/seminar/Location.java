package seminar;

public class Location {
	private String _name;
	private int _seats;

	public Location(String name, int seats) {
		_name = name;
		_seats = seats;
	}

	public String getName() {
		return _name;
	}

	public int getSeats() {
		return _seats;
	}
}
