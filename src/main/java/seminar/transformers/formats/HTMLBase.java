package seminar.transformers.formats;

import java.util.List;

public class HTMLBase {
	private StringBuilder _html;

	public HTMLBase() {
		_html = new StringBuilder();
		_html.append("<html><head></head><body></body></html>");
	}

	public void title(String value) {
		append("<title>" + value + "</title>", "</head>");
	}

	public void div(String value) {
		append("<div>" + value + "</div>", "</body>");
	}

	public void ul(List<String> listItems) {
		append("<ul></ul>", "</body>");
		for(String li: listItems) {
			append("<li>" + li + "</li>", "</ul></body>");
		}
	}

	private void append(String value, String tag) {
		int position = _html.toString().indexOf(tag);
		_html.insert(position, value);
	}

	public String render() {
		return _html.toString();
	}
}
