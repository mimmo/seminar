package seminar;

public class SeminarBuilderFactory {

	public static Seminar.Builder create() {
		return Seminar
			.builder()
			.course(new Course("Requirements Analysis", 1, "Identify and write scenarios"))
			.location(new Location("The main room", 3));
	}

}
