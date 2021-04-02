package semina.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import seminar.Course;
import seminar.context.Context;
import seminar.context.Request;
import seminar.server.controller.CourseCreateController;


public class CourseCreateControllerTest {

	private List<Course> _coursePersistence;
	private CourseCreateController _courseCreateController;

	@Before
	public void setUp() {
		_coursePersistence = new ArrayList<>();
		_courseCreateController = new CourseCreateController(_coursePersistence);
	}

	@Test
	public void routeTest() {
		assertThat(_courseCreateController.handles("/course/create")).isTrue();
	}

	@Test
	public void ok() {
		Request request = new Request(Map.of(
			"name", new String[]{"a name"},
			"number", new String[] {"1"},
			"description", new String[] {"lorem ipsum"}
		));
		Context context = new Context(request);

		_courseCreateController.execute(context);

		assertThat(context.getResponse().getRedirectURL()).isEqualTo("/courses");
		assertThat(_coursePersistence.size()).isEqualTo(1);
		Course course = _coursePersistence.get(0);
		assertThat(course.getName()).isEqualTo("a name");
		assertThat(course.getNumber()).isEqualTo(1);
		assertThat(course.getDescription()).isEqualTo("lorem ipsum");
	}

	@Test
	public void createAnotherCourse() {
		_coursePersistence.add(new Course("name", 1, "description"));
		Request request = new Request(Map.of(
			"name", new String[]{"a name"},
			"number", new String[] {"1"},
			"description", new String[] {"lorem ipsum"}
		));
		Context context = new Context(request);

		_courseCreateController.execute(context);

		assertThat(_coursePersistence.size()).isEqualTo(2);
	}

}
