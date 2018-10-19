package com.mentoring.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
/*
 * PMD Checks:
 * ControlStatementBraces: added braces around if else
 * CyclomaticComplexity: refactored  indexOf in if check
 * UncommentedEmptyMethodBody: init and destroy methods are not used
 *
 */
public class AuthorizationFilter implements Filter {

	private final List<String> urls = Arrays.asList("/login.xhtml",
			"/home-page.",
			"/quizz.xhtml",
			"/singup.xhtml",
			"/user-interface.",
			"/singup.xhtml",
			"/footer.xhtml",
			"/about.xhtml");

	public AuthorizationFilter() {
	}


	public void init(FilterConfig filterConfig) throws ServletException {

	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			boolean containsOneOf = urls.stream().anyMatch(reqURI::contains);
			if (containsOneOf	|| (ses != null && ses.getAttribute("username") != null)) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void destroy() {

	}
}