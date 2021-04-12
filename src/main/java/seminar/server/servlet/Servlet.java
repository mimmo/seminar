package seminar.server.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _ch.gmtech.gmcommons.database.ConnectionFactory;
import seminar.context.Context;
import seminar.context.Request;
import seminar.server.controller.Controller;
import seminar.server.controller.ControllerFactory;
import seminar.server.servlet.context.HttpResponseWriter;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ConnectionFactory _connectionFactory;

	@Override
	public void init() throws ServletException {
		_connectionFactory = (ConnectionFactory) getServletContext().getAttribute("connectionFactory");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = _connectionFactory.get();
		for(Controller c : new ControllerFactory(connection).create()){
			if(c.handles(req.getRequestURI())){
				try {
					Context context = new Context(new Request(req.getParameterMap()));
					c.execute(context);
					HttpResponseWriter.apply(resp, context.getResponse());
					connection.commit();
					return;
				} catch (Exception e) {
					try {
						connection.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					throw new RuntimeException(e);
				} finally {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
}
