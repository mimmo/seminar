package seminar.context;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private Map<String, String[]> _payload;

	public Request() {
		this(new HashMap<>());
	}

	public Request(Map<String, String[]> payload) {
		this._payload = payload;
	}

	public String[] getParameter(String name) {
		return _payload.get(name);
	}

}
