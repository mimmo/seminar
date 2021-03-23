package composition;

public class Composition {

	public static void main(String[] args) {

		MotorBike bike = MotorBike.yamaha600();
		System.out.println(bike.getBrand());
		System.out.println(bike.getEngine().size());
		System.out.println(bike.getElectronic());
		System.out.println(bike.asLog());
		bike.log();
	}

}
