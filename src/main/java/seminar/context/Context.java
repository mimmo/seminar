package seminar.context;

public class Context {

	private Response _response;
	private Request _request;

	public Context() {
		this(new Request());
	}

	public Context(Request request) {
		_request = request;
		_response = Response.builder().build();
	}

	public void add(Response response) {
		_response = response;
	}

	public Response getResponse() {
		return _response;
	}

	public Request getRequest() {
		return _request;
	}

}
