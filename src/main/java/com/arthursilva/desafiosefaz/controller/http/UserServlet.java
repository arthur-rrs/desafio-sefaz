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

@WebServlet(name = "UserServlet", urlPatterns = { "/user" })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idInput = req.getParameter("id");
		String body = "";
		int status = 200;
		Gson gson = new Gson();
		UserRepository repository = (new UserRepositoryFactory()).getUserRepository();
		if (null != idInput) {
			User user = repository.get(Long.parseLong(idInput));
			status = user == null ? 404 : status;
			body = gson.toJson(user);
		} else {
			List<User> users = repository.findAll();
			body = gson.toJson(users);
		}
		this.response(resp, status, body);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong((String) req.getParameter("id"));
		UserRepository repository = (new UserRepositoryFactory()).getUserRepository();
		repository.removeById(id);
		this.response(resp, 204, "");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepository repository = (new UserRepositoryFactory()).getUserRepository();
		String body = "";
		Gson gson = new GsonBuilder().create();
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
			for (ConstraintViolation<User> violation : constraintViolations) {
				errors.add(violation.getMessage());
			}
			body = gson.toJson(errors);
			this.response(resp, 400, body);
			return;
		}
		repository.update(user);
		body = gson.toJson(user);
		this.response(resp, 201, body);
	}

	private void response(HttpServletResponse resp, int status, String body) throws IOException {
		resp.setContentType("application/json");
		resp.setStatus(status);
		resp.getWriter().write(body);
	}

}
