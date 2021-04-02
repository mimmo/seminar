package seminar.server.controller;

import seminar.context.Context;

public interface Controller {

	boolean handles(String route);
	void execute(Context context) throws Exception;
}
