package semina.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import seminar.Seminar;
import seminar.SeminarBuilderFactory;
import seminar.context.Context;
import seminar.server.controller.Controller;
import seminar.server.controller.HTMLController;
import seminar.transformers.SeminarHTMLTransformer;

public class HTMLControllerTest {
	@Test
	public void routeTest() {
		assertThat(new HTMLController(List.of(SeminarBuilderFactory.create().build())).handles("/course/html")).isTrue();
	}

	@Test
	public void executionTest() throws Exception {
		Seminar seminar = SeminarBuilderFactory.create().build();
		Controller htmlController = new HTMLController(List.of(seminar));
		Context context = new Context();
		htmlController.execute(context);
		assertThat(context.getResponse().getContentType()).isEqualTo("text/html");
		assertThat(context.getResponse().getCharset()).isEqualTo("UTF-8");
		assertThat(context.getResponse().getPayload()).isEqualTo(new SeminarHTMLTransformer().apply(seminar));
	}
}
