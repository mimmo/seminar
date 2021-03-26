package seminar.server.controller;

import seminar.server.servlet.Context;

public interface Controller {

	boolean handles(String route);
	void execute(Context context) throws Exception;
}
