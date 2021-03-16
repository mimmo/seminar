package seminar;

public class StudentFactory {

	public static Student marioRossi() {
		return new Student("Mario", "Rossi");
	}

	public static Student marioBianchi() {
		return new Student("Mario", "Bianchi");
	}

	public static Student paoloVerdi() {
		return new Student("Paolo", "Verdi");
	}

	public static Student giovanniNeri() {
		return new Student("Giovanni", "Neri");
	}

}
