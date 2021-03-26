package seminar;

public class SeminarDetailsRAW extends SeminarDetails {
	public SeminarDetailsRAW(Seminar seminar) {
		super(seminar);
	}

	@Override
	public String header() {
		return
			"Number: " + _seminar.getCourse().getNumber() + "\n" +
			"Name: " + _seminar.getCourse().getName();
	}

	@Override
	public String courseInfo() {
		// location, description and seatsleft
		return
			"Location: " + _seminar.getLocation().getName() + "\n" +
			"Description: " + _seminar.getDescritpion() + "\n" +
			"Seats left: " + _seminar.getSeatsLeft() + "\n";
	}

	@Override
	public String studentLine(String s) {
		return "- " + s + "\n";
	}

	@Override
	public String footer() {
		return "---\n";
	}
}
