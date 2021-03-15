package seminar;

public class SeminarDetails {
	private Seminar _seminar;

	public SeminarDetails(Seminar seminar) {
		_seminar = seminar;
	}

//	public String html() {
//		Element html = new Element("html");
//		Element head = new Element("head");
//		Element body = new Element("body");
//
//		html.appendChild(head);
//		head.appendElement("title").text(_seminar.getName().toString());
//		html.appendChild(body);
//		body.appendElement("div").text(_seminar.getName().toString() + ":");
//		Element courseInfo = body.appendElement("ul");
//		courseInfo.appendElement("li").text(_seminar.getDescritpion());
//		courseInfo.appendElement("li").text(_seminar.getLocation());
//		courseInfo.appendElement("li").text(String.valueOf(_seminar.getSeatsLeft()));
//		body.appendElement("div").text("partecipanti:");
//		Element participants = body.appendElement("ul");
//		for(String s: _seminar.getStudentsList()) {
//			participants.appendElement("li").text(s);
//		}
//
//		return html.toString();
//	}

	public String html() {
		String html = "<html><head><title>";
		html += _seminar.getName();
		html += "</title><body><div>";
		html += _seminar.getName();
		html += ":</div><ul><li>";
		html += _seminar.getDescritpion();
		html += "</li><li>";
		html += _seminar.getLocation();
		html += "</li><li>";
		html += String.valueOf(_seminar.getSeatsLeft());
		html += "</li></ul><div>partecipanti:</div><ul>";
		for(String s: _seminar.getStudentsList()) {
			html += "<li>" + s + "</li>";
		}
		html += "</ul>";
		html += "</body></html>";

		return html;
	}

}
