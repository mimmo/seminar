package seminar.server.servlet;

public class Context {

	private Response _response;

	public Context() {
		_response = Response.builder().build();
	}

	public void add(Response response) {
		_response = response;
	}

	public Response getResponse() {
		return _response;
	}

}
