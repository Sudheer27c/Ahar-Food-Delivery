package com.tap.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if(session == null ||
				session.getAttribute("loggedInUser") == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		req.getRequestDispatcher(
				"profile.jsp")
		.forward(req, resp);
	}
}