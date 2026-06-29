package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/userManagement")
public class UserManagementServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		

		UserDAOImpl dao =
				new UserDAOImpl();

		List<User> users =
				dao.getAllUsers();


		req.setAttribute(
				"users",
				users);

		req.getRequestDispatcher(
				"users.jsp")
		.forward(req, resp);
	}
}