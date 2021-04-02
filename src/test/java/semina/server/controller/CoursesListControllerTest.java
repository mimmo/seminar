package semina.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seminar.Course;
import seminar.context.Context;
import seminar.server.controller.CoursesListController;

public class CoursesListControllerTest {
	private List<Course> _coursePersistence;
	private CoursesListController _coursesListController;

	@Before
	public void setUp() {
		_coursePersistence = new ArrayList<>();
		_coursesListController = new CoursesListController(_coursePersistence);
	}

	@Test
	public void routeTest() {
		assertThat(_coursesListController.handles("/courses"));
	}

	@Test
	public void listWithoutCourses() {
		Context context = new Context();

		_coursesListController.execute(context);

		assertThat(_coursePersistence.size()).isEqualTo(0);
		assertThat(context.getResponse().getContentType()).isEqualTo("text/html");
		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
		assertThat(context.getResponse().getPayload()).isEqualTo("");
	}

//	@Test
//	public void listWithCourses() {
//		_coursePersistence.add(new Course("name", 1, "description"));
//		Context context = new Context();
//
//		_coursesListController.execute(context);
//
//		assertThat(_coursePersistence.size()).isEqualTo(0);
//		assertThat(context.getResponse().getContentType()).isEqualTo("text/html");
//		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
//		assertThat(context.getResponse().getPayload()).isEqualTo("");
//	}

}
