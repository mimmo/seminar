package seminar.server.servlet.context;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import seminar.context.Response;

public class HttpResponseWriter {

	public static void apply(HttpServletResponse httpResponse, Response response) throws Exception {
		if(!response.getRedirectURL().isBlank()) {
			httpResponse.sendRedirect(response.getRedirectURL());
			return;
		}

		httpResponse.setCharacterEncoding(response.getCharset());
		httpResponse.setContentType(response.getContentType());
		response.getHeaders()
			.entrySet()
			.forEach(entry -> httpResponse.addHeader(entry.getKey(), entry.getValue()));
		PrintWriter writer = httpResponse.getWriter();
		writer.write(response.getPayload());
		writer.flush();
		writer.close();
	}

}
