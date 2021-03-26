package seminar.server.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {
	private String contentType;
	private Map<String, String> headers = new HashMap<>();
	private String charset;
	private String payload;

	public static Response.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String contentType;
		private Map<String, String> headers;
		private String charset;
		private String payload;

		public Response build() {
			Response result = new Response();
			result.contentType = contentType;
			result.headers = headers;
			result.charset = charset;
			result.payload = payload;
			return result;
		}

		public Builder contentType(String type) {
			this.contentType = type;
			return this;
		}

		public Builder header(String name, String value) {
			this.headers.put(name, value);
			return this;
		}

		public Builder charset(String charset) {
			this.charset = charset;
			return this;
		}
		public Builder payload(String payload) {
			this.payload = payload;
			return this;
		}

		private Builder() {
			headers = new HashMap<>();
			charset("UTF-8");
			contentType("text/plain");
			payload("");
		}

	}

	public void applyOn(PrintWriter writer) {
		writer.write(payload);
	}

	public String getContentType() {
		return contentType;
	}
	public String getCharset() {
		return charset;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public String getPayload() {
		return payload;
	}

	private Response() {}

}
