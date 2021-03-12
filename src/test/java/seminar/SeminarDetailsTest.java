package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsTest {
	private Course _course;
	private Location _location;
	private Seminar _seminar;
	private Student _student;
	private Document _doc;

	@Before
	public void setUp() {
		_course = new Course("Requirements Analysis", 1, "Identify and write scenarios");
		_location = new Location("The main room", 50);
		_student = new Student("Mario", "Rossi");
		_seminar = new Seminar(_location, _course);
		_doc = Jsoup.parse(new SeminarDetails(_seminar).html());
	}

	@Test
	public void courseWithoutStudents() {
		assertThat(_doc.title()).isEqualTo("Requirements Analysis#1");
		assertThat(_doc.select("ul li:nth-child(1)").text()).isEqualTo("Identify and write scenarios");
		assertThat(_doc.select("ul li:nth-child(2)").text()).isEqualTo("The main room");
		assertThat(_doc.select("ul li:nth-child(3)").text()).isEqualTo("50");
	}

	@Test
	public void courseWithOneStudent() {
		_seminar.addStudent(_student);
		_doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(_doc.select("ul li:nth-child(3)").text()).isEqualTo("49");
		assertThat(_doc.select("ul + div + ul li").text()).isEqualTo("Mario Rossi");
	}

	@Test
	public void courseWithManyStudents() {
		_seminar.addStudent(_student);
		_seminar.addStudent(new Student("Luigi", "Bianchi"));
		_doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(_doc.select("ul + div + ul li:nth-child(1)").text()).isEqualTo("Mario Rossi");
		assertThat(_doc.select("ul + div + ul li:nth-child(2)").text()).isEqualTo("Luigi Bianchi");
	}




}
