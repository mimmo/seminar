package seminar.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seminar.Course;
import seminar.Seminar;
import seminar.context.Context;
import seminar.context.Request;
import seminar.server.controller.Controller;
import seminar.server.controller.ControllerFactory;
import seminar.server.servlet.context.HttpResponseWriter;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<Course> _persistence;
	private List<Seminar> _seminars;

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		_persistence = (List<Course>) getServletContext().getAttribute("persistence");
		_seminars = (List<Seminar>) getServletContext().getAttribute("seminars");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for(Controller c : new ControllerFactory(_persistence, _seminars).create()){
			if(c.handles(req.getRequestURI())){
				try {
					Context context = new Context(new Request(req.getParameterMap()));
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
