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
	public void HTMLcourseHeader() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsHTML html = new SeminarDetailsHTML(_seminar);
		assertThat(html.header()).isEqualTo("<html><head><title>Requirements Analysis#1</title></head><body><div>Requirements Analysis#1</div>");
	}

	@Test
	public void HTMLcourseInfo() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsHTML html = new SeminarDetailsHTML(_seminar);
		assertThat(html.courseInfo()).isEqualTo("<ul><li>Identify and write scenarios</li><li>The main room</li><li>3</li></ul><div>partecipanti:</div><ul>");
	}

	@Test
	public void HTMLstudentLine() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsHTML html = new SeminarDetailsHTML(_seminar);
		assertThat(html.studentLine(StudentFactory.marioRossi().getFullName())).isEqualTo("<li>Mario Rossi</li>");
	}

	@Test
	public void HTMLfooter() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsHTML html = new SeminarDetailsHTML(_seminar);
		assertThat(html.footer()).isEqualTo("</ul></body></html><hr>");
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
