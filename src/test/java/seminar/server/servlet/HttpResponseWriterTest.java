package seminar.server.servlet;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

public class HttpResponseWriterTest {


	@Test
	public void test() throws Exception {
		StringWriter out = new StringWriter();
		HttpServletResponse httpResponse = new HttpResponseSpiable(new PrintWriter(out));
		HttpResponseWriter.apply(httpResponse, Response
			.builder()
			.contentType("text/csv")
			.header("Content-Disposition", "attachment; fileName=data.csv")
			.payload("a,b,c")
			.build());

		assertThat(httpResponse.getContentType()).isEqualTo("text/csv");
		assertThat(httpResponse.containsHeader("Content-Disposition")).isTrue();
		assertThat("a,b,c").isEqualTo(out.toString());
	}

	public class HttpResponseSpiable implements HttpServletResponse {

		private String contentType;
		private Map<String, String> headers = new HashedMap<>();
		private PrintWriter writer;

		public HttpResponseSpiable(PrintWriter writer) {
			this.writer = writer;
		}

		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContentType() {
			return contentType;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return writer;
		}

		@Override
		public void setCharacterEncoding(String charset) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setContentLength(int len) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setContentType(String type) {
			contentType = type;
		}

		@Override
		public void setBufferSize(int size) {
			// TODO Auto-generated method stub

		}

		@Override
		public int getBufferSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void flushBuffer() throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public void resetBuffer() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isCommitted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub

		}

		@Override
		public void setLocale(Locale loc) {
			// TODO Auto-generated method stub

		}

		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addCookie(Cookie cookie) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean containsHeader(String name) {
			return !headers.get(name).isBlank();
		}

		@Override
		public String encodeURL(String url) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeRedirectURL(String url) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeUrl(String url) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeRedirectUrl(String url) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void sendError(int sc, String msg) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public void sendError(int sc) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public void sendRedirect(String location) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setDateHeader(String name, long date) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addDateHeader(String name, long date) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setHeader(String name, String value) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addHeader(String name, String value) {
			headers.put(name, value);
		}

		@Override
		public void setIntHeader(String name, int value) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addIntHeader(String name, int value) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setStatus(int sc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setStatus(int sc, String sm) {
			// TODO Auto-generated method stub

		}

	}

}
