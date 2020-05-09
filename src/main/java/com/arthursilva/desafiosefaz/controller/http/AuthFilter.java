
package com.arthursilva.desafiosefaz.controller.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arthursilva.desafiosefaz.model.entity.User;

@WebFilter(servletNames = { "UserServlet" }, urlPatterns = { "/index.jsp" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		User user = null;
		if (requestHttp.getSession(false) != null) {
			user = (User) requestHttp.getSession(false).getAttribute("user");
		}
		if (null == user) {
			HttpServletResponse responseHttp = (HttpServletResponse) response;
			responseHttp.setStatus(401);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

}
