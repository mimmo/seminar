package composition;

public class MotorBike implements Loggable, Logger {

	private Engine engine;
	private String brand;
	private Electronic electronic;

	public static MotorBike yamaha600() {
		MotorBike res = new MotorBike("yahama", new Engine(600));
		res.electronic = new YecElectronic();
		return res;
	}

	private MotorBike(String brand, Engine engine) {
		this.brand = brand;
		this.engine = engine;
		this.electronic = new NullElectronic();
	}

	public String getBrand() {
		return brand;
	}
	public Engine getEngine() {
		return engine;
	}
	public Electronic getElectronic() {
		return this.electronic;
	}

	@Override
	public String asLog() {
		return brand;
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub

	}

}
