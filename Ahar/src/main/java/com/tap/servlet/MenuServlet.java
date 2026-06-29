package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		// Login Validation
		if (session == null ||
				session.getAttribute("loggedInUser") == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		try {

			String rid =
					req.getParameter("restaurantId");

			// Restaurant Id Validation
			if (rid == null || rid.trim().isEmpty()) {

				resp.sendRedirect("home");
				return;
			}

			int restaurantId =
					Integer.parseInt(rid);

			MenuDAOImpl dao =
					new MenuDAOImpl();

			List<Menu> menuList =
					dao.getMenuByRestaurantId(
							restaurantId);

			req.setAttribute(
					"menuList",
					menuList);

			RequestDispatcher rd =
					req.getRequestDispatcher(
							"/menu.jsp");

			rd.forward(req, resp);

		}
		catch (NumberFormatException e) {

			System.out.println(
					"Invalid Restaurant ID");

			resp.sendRedirect("home");
		}
		catch (Exception e) {

			e.printStackTrace();

			req.setAttribute(
					"errorMessage",
					"Unable to load menu items");

			req.getRequestDispatcher(
					"/error.jsp")
			.forward(req, resp);
		}
	}
}