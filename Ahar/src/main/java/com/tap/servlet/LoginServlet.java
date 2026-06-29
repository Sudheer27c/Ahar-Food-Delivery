package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		resp.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if(email == null || email.trim().isEmpty() ||
				password == null || password.trim().isEmpty()) {

			resp.sendRedirect(
					"login.jsp?error=Enter Email And Password");
			return;
		}

		UserDAOImpl dao = new UserDAOImpl();

		User user = dao.getUserByEmail(email);

		if(user != null &&
				user.getPassword().equals(password)) {

			dao.updateLastLogin(user.getUserId());

			HttpSession session =
					req.getSession(true);

			// 30 minutes session timeout
			session.setMaxInactiveInterval(30 * 60);

			session.setAttribute(
					"loggedInUser",
					user);
			
			
			session.setAttribute(
					"userName",
					user.getUsername());

			session.setAttribute(
					"role",
					user.getRole());

			if(user.getRole() != null &&
					user.getRole().equalsIgnoreCase("admin")) {

				resp.sendRedirect(
						"adminDashboard");

			} else {

				resp.sendRedirect(
						"home");
			}

		} else {

			resp.sendRedirect(
					"login.jsp?error=Invalid Email Or Password");
		}
	}
}