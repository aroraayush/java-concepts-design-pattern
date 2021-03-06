package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.text.StringEscapeUtils;

/** An example that demonstrates how to process HTML forms with servlets. 
 *  Part of the example that also includes HtmlFormServer and HtmlFormServlet.
 */

@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println();

		PrintWriter out = response.getWriter();
		out.printf("<html>%n%n");
		out.printf("<head><title>%s</title></head>%n", "Form");
		out.printf("<body>%n");
		
		// Displays Hello and the name of the person
		out.printf("<h1>Hello, %s!</h1>%n%n", StringEscapeUtils.escapeHtml4(request.getParameter("name")));
		
		out.printf("%n</body>%n");
		out.printf("</html>%n");

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
	}


}
