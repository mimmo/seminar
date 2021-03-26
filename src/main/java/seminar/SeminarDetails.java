package seminar;

public abstract class SeminarDetails {
	public Seminar _seminar;

	public SeminarDetails(Seminar seminar) {
		_seminar = seminar;
	}

	public String render() {
		String result = header();
		result += courseInfo();

		for (String s : _seminar.getStudentsList()) {
			result += studentLine(s);
		}

		result += footer();

		return result;
	}

	public String footer() {
		return "";
	}

	public abstract String header();
	public abstract String courseInfo();
	public abstract String studentLine(String s);
}
