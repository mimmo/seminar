package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsTest {
	private Document _doc;
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
		_doc = Jsoup.parse(new SeminarDetails(_seminarBuilder.build()).html());
	}

	@Test
	public void courseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		assertThat(_doc.title()).isEqualTo("Requirements Analysis#1");
		assertThat(_doc.select("ul li:nth-child(1)").text()).isEqualTo(_seminar.getDescritpion());
		assertThat(_doc.select("ul li:nth-child(2)").text()).isEqualTo(_seminar.getLocation().getName());
		assertThat(_doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats()));
	}

	@Test
	public void courseWithOneStudent() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		_doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(_doc.select("ul li:nth-child(3)").text()).isEqualTo(String.valueOf(_seminar.getLocation().getSeats() - 1));
		assertThat(_doc.select("ul + div + ul li").text()).isEqualTo(_seminar.getStudentsList().get(0));
	}

	@Test
	public void courseWithManyStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		_seminarBuilder.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		_doc = Jsoup.parse(new SeminarDetails(_seminar).html());
		assertThat(_doc.select("ul + div + ul li:nth-child(1)").text()).isEqualTo(_seminar.getStudentsList().get(0));
		assertThat(_doc.select("ul + div + ul li:nth-child(2)").text()).isEqualTo(_seminar.getStudentsList().get(1));
	}




}
