package seminar;

public class SeminarDetailsHTML implements SeminarDetails {
	 private Seminar _seminar;

	 public SeminarDetailsHTML(Seminar seminar) {
	 _seminar = seminar;
	 }

	@Override
	public String render() {
		String html = "<html><head><title>";
		html += _seminar.getName();
		html += "</title><body><div>";
		html += _seminar.getName();
		html += ":</div><ul><li>";
		html += _seminar.getDescritpion();
		html += "</li><li>";
		html += _seminar.getLocation().getName();
		html += "</li><li>";
		html += String.valueOf(_seminar.getSeatsLeft());
		html += "</li></ul><div>partecipanti:</div><ul>";
		for (String s : _seminar.getStudentsList()) {
			html += "<li>" + s + "</li>";
		}
		html += "</ul>";
		html += "</body></html>";

		return html;
	}

}
