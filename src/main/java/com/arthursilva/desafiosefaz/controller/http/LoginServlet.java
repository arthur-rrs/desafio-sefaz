package com.arthursilva.desafiosefaz.controller.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arthursilva.desafiosefaz.model.entity.User;
import com.arthursilva.desafiosefaz.repository.UserRepository;
import com.arthursilva.desafiosefaz.repository.factory.UserRepositoryFactory;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getParameter("email");
		String password = (String) req.getParameter("password");
		String error = "";
		String path = "/index.jsp";
		UserRepository repository = (new UserRepositoryFactory()).getUserRepository();
		User user = repository.findByEmail(email);
		if (null == user) {
			error = "Email não encontrado.";
			path = "/login.jsp";
		} else if (false == user.getPassword().equals(password)) {
			error = "Senha incorreta." + password + " == "+ user.getPassword();
			path = "/login.jsp";
		} else {
			req.getSession().setAttribute("user", user);
		} 
		req.setAttribute("error", error);
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
