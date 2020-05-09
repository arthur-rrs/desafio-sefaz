package com.arthursilva.desafiosefaz.controller.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.arthursilva.desafiosefaz.model.entity.User;
import com.arthursilva.desafiosefaz.repository.UserRepository;
import com.arthursilva.desafiosefaz.repository.factory.UserRepositoryFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(name = "RegisterUserServlet", urlPatterns = { "/register" })
public class RegisterUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepository repository = (new UserRepositoryFactory()).getUserRepository();
		String body = "";
		Gson gson = new GsonBuilder()
	            .create();
		String input = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		User user = (User) gson.fromJson(input, User.class);
		if (null == user) {
			body = gson.toJson("Erro na Formatação do Corpo da Requisição");
			this.response(resp, 400, body);
			return;
		}
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			List<String> errors = new ArrayList<String>();
			for (ConstraintViolation<User> violation : constraintViolations ) {
				errors.add(violation.getMessage());
			}
			body = gson.toJson(errors);
			this.response(resp, 400, body);
			return;
		}
		repository.save(user);
		body = gson.toJson(user);
		this.response(resp, 201, body);
	}

	private void response(HttpServletResponse resp, int status, String body) throws IOException {
		resp.setContentType("application/json");
		resp.setStatus(status);
		resp.getWriter().write(body);
	}
}
