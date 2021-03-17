package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsTest {
	private Document doc;
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void HTMLcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(doc.title()).isEqualTo("Requirements Analysis#1");
		assertThat(doc.select("ul li:nth-child(1)").text()).isEqualTo(_seminar.getDescritpion());
		assertThat(doc.select("ul li:nth-child(2)").text()).isEqualTo(_seminar.getLocation().getName());
		assertThat(doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats()));
	}

	@Test
	public void HTMLcourseWithOneStudent() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats() - 1));
		assertThat(doc.select("ul + div + ul li").text()).isEqualTo(_seminar.getStudentsList().get(0));
	}

	@Test
	public void HTMLcourseWithManyStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		_seminarBuilder.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(doc.select("ul + div + ul li:nth-child(1)").text()).isEqualTo(_seminar.getStudentsList().get(0));
		assertThat(doc.select("ul + div + ul li:nth-child(2)").text()).isEqualTo(_seminar.getStudentsList().get(1));
	}

	@Test
	public void CSVcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetails(_seminar).csv().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;3");
	}

	@Test
	public void CSVcourseWithOneStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetails(_seminar).csv().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;2");
		assertThat(csv[1]).isEqualTo("Mario;Rossi");
	}

	@Test
	public void CSVcourseWithManyStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		_seminarBuilder.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		String[] csv = new SeminarDetails(_seminar).csv().split("\n");
		assertThat(csv[0]).isEqualTo("1;Requirements Analysis;Identify and write scenarios;The main room;1");
		assertThat(csv[1]).isEqualTo("Mario;Rossi");
		assertThat(csv[2]).isEqualTo("Mario;Bianchi");
	}

}
