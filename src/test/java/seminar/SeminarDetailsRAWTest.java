package seminar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SeminarDetailsRAWTest {
	private Seminar.Builder _seminarBuilder;

	@Before
	public void setUp() {
		_seminarBuilder = SeminarBuilderFactory.create();
	}

	@Test
	public void HTMLcourseHeader() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsRAW html = new SeminarDetailsRAW(_seminar);
		assertThat(html.header()).isEqualTo("Number: 1\nName: Requirements Analysis");
	}

	@Test
	public void HTMLcourseInfo() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsRAW html = new SeminarDetailsRAW(_seminar);
		assertThat(html.courseInfo()).isEqualTo("Location: The main room\n" +
			"Description: Identify and write scenarios\n" +
			"Seats left: 3\n");
	}

	@Test
	public void HTMLstudentLine() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsRAW html = new SeminarDetailsRAW(_seminar);
		assertThat(html.studentLine(StudentFactory.marioRossi().getFullName())).isEqualTo("- Mario Rossi\n");
	}

	@Test
	public void HTMLfooter() {
		Seminar _seminar = _seminarBuilder.build();
		SeminarDetailsRAW html = new SeminarDetailsRAW(_seminar);
		assertThat(html.footer()).isEqualTo("---\n");
	}

	@Test
	public void CSVcourseWithoutStudents() {
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarDetailsRAW(_seminar).render().split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements AnalysisLocation: The main room");
		assertThat(raw[2]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[3]).isEqualTo("Seats left: 3");
	}

	@Test
	public void CSVcourseWithOneStudents() {
		_seminarBuilder.student(StudentFactory.marioRossi());
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarDetailsRAW(_seminar).render().split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements AnalysisLocation: The main room");
		assertThat(raw[2]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[3]).isEqualTo("Seats left: 2");
		assertThat(raw[4]).isEqualTo("- Mario Rossi");
	}

	@Test
	public void CSVcourseWithManyStudents() {
		_seminarBuilder
			.student(StudentFactory.marioRossi())
			.student(StudentFactory.marioBianchi());
		Seminar _seminar = _seminarBuilder.build();
		String[] raw = new SeminarDetailsRAW(_seminar).render().split("\n");
		assertThat(raw[0]).isEqualTo("Number: 1");
		assertThat(raw[1]).isEqualTo("Name: Requirements AnalysisLocation: The main room");
		assertThat(raw[2]).isEqualTo("Description: Identify and write scenarios");
		assertThat(raw[3]).isEqualTo("Seats left: 1");
		assertThat(raw[4]).isEqualTo("- Mario Rossi");
		assertThat(raw[5]).isEqualTo("- Mario Bianchi");
	}
}
