package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		String userIdParam =
				req.getParameter("userId");

		System.out.println(
				"User ID Param = " +
						userIdParam);

		if(userIdParam == null ||
				userIdParam.trim().isEmpty()) {

			resp.sendRedirect(
					"userManagement");
			return;
		}

		try {

			int userId =
					Integer.parseInt(
							userIdParam);

			UserDAOImpl dao =
					new UserDAOImpl();

			dao.deleteUser(userId);

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		resp.sendRedirect(
				"userManagement");
	}
}