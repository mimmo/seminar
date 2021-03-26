package seminar.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seminar.Seminar;
import seminar.server.controller.Controller;
import seminar.server.controller.ControllerFactory;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Seminar> _seminars;

	@Override
	public void init() throws ServletException {
		_seminars = (List<Seminar>) getServletContext().getAttribute("seminars");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for(Controller c : new ControllerFactory(_seminars).create()){
			if(c.handles(req.getRequestURI())){
				try {
					Context context = new Context();
					c.execute(context);
					HttpResponseWriter.apply(resp, context.getResponse());
					return;
				} catch (Exception e) {
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					throw new RuntimeException(e);
				}
			}
		}
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
}
