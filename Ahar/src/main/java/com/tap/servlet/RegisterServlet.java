package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		resp.sendRedirect("register.jsp");
	}

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String role = req.getParameter("role");

		// Validation
		if(name == null || name.trim().isEmpty() ||
				email == null || email.trim().isEmpty() ||
				password == null || password.trim().isEmpty()) {

			resp.sendRedirect(
					"register.jsp?error=Please Fill All Required Fields");
			return;
		}

		UserDAOImpl dao = new UserDAOImpl();

		if(dao.emailExists(email)) {

			resp.sendRedirect(
					"register.jsp?error=Email Already Exists");
			return;
		}

		User user = new User(
				name,
				password,
				email,
				address,
				role
				);

		int result = dao.addUser(user);

		if(result > 0) {

			resp.sendRedirect(
					"login.jsp?success=Registration Successful");

		} else {

			resp.sendRedirect(
					"register.jsp?error=Registration Failed");
		}
	}
}