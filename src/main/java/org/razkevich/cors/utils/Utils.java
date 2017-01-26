package org.razkevich.cors.utils;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public final class Utils {

	private static final String SSO_ALLOWED_ORIGIN = "http://10.80.17.236:8070";
	public static final String SSO_COOKIE_NAME = "SSO_COOKIE_NAME";

	public static final String SSO_AUTH_URL = "http://10.80.18.202:8080/cors-poc/auth";
	private static final int SSO_COOKIE_MAX_AGE = 12 * 60 * 60;
	private static final String SSO_COOKIE_CHAR_SET = "AQBHYv8lU1tKGSDmc02IW67%59jrFpNOXz4e";
	private static final int SSO_COOKIE_LENGTH = 50;

	private Utils() {
	}

	public static void setSsoCookieHeader(HttpServletResponse rs) {
		rs.setHeader("SET-COOKIE",
				SSO_COOKIE_NAME + "=" + generateCookieValue() +
						"; Path=" + "/" +
						"; Max-Age=" + SSO_COOKIE_MAX_AGE +
						"; HttpOnly");
	}

	private static String generateCookieValue() {
		Random random = new Random();
		char[] value = new char[SSO_COOKIE_LENGTH];
		for (int i = 0; i < SSO_COOKIE_LENGTH; i++) {
			value[i] = SSO_COOKIE_CHAR_SET.charAt(random.nextInt(SSO_COOKIE_CHAR_SET.length()));
		}
		return new String(value);
	}

	public static void setCorsHeaders(HttpServletResponse rs) {
		rs.setHeader("Access-Control-Allow-Origin", SSO_ALLOWED_ORIGIN);
		rs.setHeader("Access-Control-Allow-Credentials", "true");
		rs.setHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type");
	}
}
