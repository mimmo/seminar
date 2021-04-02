package seminar.server;

import java.util.ArrayList;
import java.util.List;

import _ch.gmtech.xremote.http.server.HttpServer;
import _ch.gmtech.xremote.http.server.HttpServerBuilder;
import seminar.Course;
import seminar.Location;
import seminar.Seminar;
import seminar.Student;
import seminar.server.servlet.Servlet;

public class SeminarHttpServer {

	public static void main(String[] args) throws Exception {
		HttpServerBuilder builder = new HttpServerBuilder();
		builder.setPort(8080);
		builder.addContextAttribute("seminars", List.of(
			Seminar
			.builder()
			.course(new Course("Requirements Analysis", 0, "Identify and write scenarios"))
			.location(new Location("The main room", 10))
			.student(new Student("Mario", "Rossi"))
			.student(new Student("Paolo", "Bianchi"))
			.build(),
			Seminar
			.builder()
			.course(new Course("Extended Requirements Analysis", 0, "Rock and roll"))
			.location(new Location("The corner room", 10))
			.student(new Student("Antonio", "Verdi"))
			.student(new Student("Roberto", "Azzurri"))
			.build()
		));
		builder.addContextAttribute("persistence", new ArrayList<Course>());

		builder.addServletMapping(new Servlet(), "/", "/*");
		HttpServer server = builder.make();
		server.start();
	}

}
