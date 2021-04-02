package seminar.transformers.formats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RAW {
	private List<String> lines;

	public RAW() {
		lines = new ArrayList<>();
	}

	public void line(String value) {
		lines.add(value);
	}

	public String render() {
		return lines.stream()
			.collect(Collectors.joining("\n"));
	}

}
