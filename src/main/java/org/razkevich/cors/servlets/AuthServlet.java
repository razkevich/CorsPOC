package org.razkevich.cors.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.razkevich.cors.utils.Utils.setCorsHeaders;
import static org.razkevich.cors.utils.Utils.setSsoCookieHeader;

public class AuthServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// [] SSO cookie header
		setSsoCookieHeader(rs);
		// [] CORS headers
		setCorsHeaders(rs);
		// [] initial URL redirect
		rs.sendRedirect(rq.getParameter("initialRqUrl"));
	}
}
