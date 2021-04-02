package seminar.transformers.formats;

import java.util.List;

import org.jsoup.nodes.Element;

public class HTMLFull {
	private Element _html;
	private Element _head;
	private Element _body;

	public HTMLFull() {
		_html = new Element("html");
		_head = new Element("head");
		_body = new Element("body");

		_html.appendChild(_head);
		_html.appendChild(_body);
	}

	public void title(String value) {
		_head.appendChild(new Element("title").text(value));
	}

	public void div(String value) {
		_body.appendChild(new Element("div").text(value));
	}

	public void ul(List<String> listItems) {
		Element ul = new Element("ul");
		for(String li: listItems) {
			ul.appendChild(new Element("li").text(li));
		}
		_body.appendChild(ul);
	}

	public String render() {
		return _html.toString();
	}
}
