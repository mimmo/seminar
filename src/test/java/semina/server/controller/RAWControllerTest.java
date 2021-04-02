package semina.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import seminar.Seminar;
import seminar.SeminarBuilderFactory;
import seminar.context.Context;
import seminar.server.controller.Controller;
import seminar.server.controller.RAWController;
import seminar.transformers.SeminarRAWTransformer;

public class RAWControllerTest {

	@Test
	public void routeTest() {
		assertThat(new RAWController(List.of(SeminarBuilderFactory.create().build())).handles("/course/raw")).isTrue();
	}

	@Test
	public void executionTest() throws Exception {
		Seminar seminar = SeminarBuilderFactory.create().build();
		Controller rawController = new RAWController(List.of(seminar));
		Context context = new Context();
		rawController.execute(context);
		assertThat(context.getResponse().getContentType()).isEqualTo("text/plain");
		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
		assertThat(context.getResponse().getPayload()).isEqualTo(new SeminarRAWTransformer().apply(seminar));
	}
}
