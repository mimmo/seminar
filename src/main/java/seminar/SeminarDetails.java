package seminar;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.internal.StringUtil;

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
		html += _seminar.getLocation().getName();
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

	public String csv() {
		String csv = Stream.of(
			String.valueOf(_seminar.getCourse().getNumber()),
			_seminar.getCourse().getName(),
			_seminar.getCourse().getDescription(),
			_seminar.getLocation().getName(),
			String.valueOf(_seminar.getSeatsLeft()) + "\n"
		).collect(Collectors.joining(";"));

		csv += _seminar.getStudentsList()
			.stream()
			.map( s -> StringUtil.join(s.split(" "), ";"))
			.collect(Collectors.joining("\n"));

		return csv;
	}
}
