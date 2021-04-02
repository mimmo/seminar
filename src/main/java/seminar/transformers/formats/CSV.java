package seminar.transformers.formats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSV {

	private List<String> rows;
	private String itemDelimiter;
	private String lineDelimiter;

	public CSV() {
		rows = new ArrayList<>();
		itemDelimiter = ";";
		lineDelimiter = "\n";
	}

	public CSV row(String ... items) {
		return row(Stream.of(items).map(CsvItem::of).toArray(CsvItem[]::new));
	}

	public CSV row(CsvItem ... items) {
		rows.add(Stream.of(items)
			.map(CsvItem::value)
			.collect(Collectors.joining(itemDelimiter, "", itemDelimiter)));
		return this;
	}

	public String render() {
		return rows
			.stream()
			.collect(Collectors.joining(lineDelimiter));
	}

	public static class CsvItem {

		private String value;

		public String value() {
			return value;
		}

		public static CsvItem of(String value) {
			return new CsvItem(String.valueOf(value));
		}
		public static CsvItem of(int number) {
			return of(String.valueOf(number));
		}

		private CsvItem(String value) {
			this.value = value;
		}

	}
}
