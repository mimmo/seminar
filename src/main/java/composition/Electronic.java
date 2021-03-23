package composition;

public interface Electronic {

	public String getName();
	public default String getLowercaseName() {
		return getName().toLowerCase();
	}

}
