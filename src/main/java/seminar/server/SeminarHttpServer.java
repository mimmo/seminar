package seminar.server;

import _ch.gmtech.gmcommons.database.ConnectionFactory;
import _ch.gmtech.xremote.http.server.HttpServer;
import _ch.gmtech.xremote.http.server.HttpServerBuilder;
import seminar.server.servlet.Servlet;

public class SeminarHttpServer {

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = ConnectionFactory.h2InMemory("test","test","test");

		HttpServerBuilder builder = new HttpServerBuilder();
		builder.setPort(8080);
		builder.addContextAttribute("connectionFactory", connectionFactory);

		builder.addServletMapping(new Servlet(), "/", "/*");
		HttpServer server = builder.make();
		server.start();
	}
}
