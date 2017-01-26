package org.razkevich.cors.servlets;

import org.razkevich.cors.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ikolosov
 */
public class CorsServlet extends HttpServlet {

	private final AtomicLong counter = new AtomicLong();

	@Override
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// [] SSO cookie lookup
		Cookie[] cookies = rq.getCookies();
		boolean found = false;
		if (cookies != null) {
			for (Cookie aCookie : cookies) {
				if (Utils.SSO_COOKIE_NAME.equals(aCookie.getName())) {
					found = true;
					break;
				}
			}
		}
		// [] CORS headers
		Utils.setCorsHeaders(rs);
		// [] auth redirect
		if (!found) {
			rs.sendRedirect(Utils.SSO_AUTH_URL + "?initialRqUrl=" + rq.getRequestURL());
			return;
		}
		// [] rs body
		rs.getWriter().println("request #" + counter.incrementAndGet());
	}
}
