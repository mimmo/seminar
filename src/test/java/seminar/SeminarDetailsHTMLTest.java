package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsHTMLTest {
	private Document doc;
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void HTMLcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetailsHTML(_seminar).render());
		assertThat(doc.title()).isEqualTo("Requirements Analysis#1");
		assertThat(doc.select("ul li:nth-child(1)").text()).isEqualTo(_seminar.getDescritpion());
		assertThat(doc.select("ul li:nth-child(2)").text()).isEqualTo(_seminar.getLocation().getName());
		assertThat(doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats()));
	}

	@Test
	public void HTMLcourseWithOneStudent() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetailsHTML(_seminar).render());
		assertThat(doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats() - 1));
		assertThat(doc.select("ul + div + ul li").text()).isEqualTo(_seminar.getStudentsList().get(0));
	}

	@Test
	public void HTMLcourseWithManyStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		_seminarBuilder.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetailsHTML(_seminar).render());
		assertThat(doc.select("ul + div + ul li:nth-child(1)").text()).isEqualTo(_seminar.getStudentsList().get(0));
		assertThat(doc.select("ul + div + ul li:nth-child(2)").text()).isEqualTo(_seminar.getStudentsList().get(1));
	}
}
