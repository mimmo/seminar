package seminar.server.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HttpResponseWriter {

	public static void apply(HttpServletResponse httpRespnse, Response response) throws Exception {
		httpRespnse.setCharacterEncoding(response.getCharset());
		httpRespnse.setContentType(response.getContentType());
		response.getHeaders()
			.entrySet()
			.forEach(entry -> httpRespnse.addHeader(entry.getKey(), entry.getValue()));
		PrintWriter writer = httpRespnse.getWriter();
		writer.write(response.getPayload());
		writer.flush();
		writer.close();
	}

}
