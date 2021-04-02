package semina.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import seminar.Seminar;
import seminar.SeminarBuilderFactory;
import seminar.context.Context;
import seminar.server.controller.CSVController;
import seminar.server.controller.Controller;
import seminar.transformers.SeminarCSVTransformer;

public class CSVControllerTest {
	@Test
	public void routeTest() {
		assertThat(new CSVController(List.of(SeminarBuilderFactory.create().build())).handles("/course/csv")).isTrue();
	}

	@Test
	public void executionTest() throws Exception {
		Seminar seminar = SeminarBuilderFactory.create().build();
		Controller csvController = new CSVController(List.of(seminar));
		Context context = new Context();
		csvController.execute(context);
		assertThat(context.getResponse().getContentType()).isEqualTo("text/csv");
		assertThat(context.getResponse().getHeaders().get("Content-Disposition")).isEqualTo("attachment; fileName=data.csv");
		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
		assertThat(context.getResponse().getPayload()).isEqualTo(new SeminarCSVTransformer().apply(seminar));
	}
}
