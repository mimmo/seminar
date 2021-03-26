package seminar;

public class SeminarDetailsCSV extends SeminarDetails {
	public SeminarDetailsCSV(Seminar seminar) {
		super(seminar);
	}

	@Override
	public String header() {
		return _seminar.getCourse().getNumber() + ";" +
			_seminar.getCourse().getName() + ";";
	}

	@Override
	public String courseInfo() {
		return _seminar.getDescritpion() + ";" +
			_seminar.getLocation().getName() + ";" +
			String.valueOf(_seminar.getSeatsLeft()) + "\n";
	}

	@Override
	public String studentLine(String s) {
		return String.join(";", s.split(" ")) + "\n";
	}
}
