package seminar;

public class SeminarDetailsHTML extends SeminarDetails {
	public SeminarDetailsHTML(Seminar seminar) {
		 super(seminar);
	 }

	@Override
	public String header() {
		return "<html><head><title>" +
			_seminar.getName() + "</title></head><body><div>" +
			_seminar.getName() + "</div>";
	}

	@Override
	public String courseInfo() {
		return "<ul><li>"  +
			_seminar.getDescritpion() + "</li><li>" +
			_seminar.getLocation().getName() + "</li><li>" +
			String.valueOf(_seminar.getSeatsLeft()) + "</li></ul><div>partecipanti:</div><ul>";
	}

	@Override
	public String studentLine(String s) {
		return "<li>" + s + "</li>";
	}

	@Override
	public String footer() {
		return "</ul></body></html><hr>";
	}

}
