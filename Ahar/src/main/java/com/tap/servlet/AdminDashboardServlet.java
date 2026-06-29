package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		// Session Validation
		if (session == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		User admin =
				(User) session.getAttribute(
						"loggedInUser");

		if (admin == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		// Optional Role Check
		if (!"admin".equalsIgnoreCase(
				admin.getRole())) {

			resp.sendRedirect("home");
			return;
		}

		try {

			UserDAOImpl userDAO =
					new UserDAOImpl();

			RestaurantDAOImpl restaurantDAO =
					new RestaurantDAOImpl();

			OrderTableDAOImpl orderDAO =
					new OrderTableDAOImpl();

			int totalUsers =
					userDAO.getUserCount();

			int totalRestaurants =
					restaurantDAO.getRestaurantCount();

			int totalOrders =
					orderDAO.getTotalOrders();

			double totalRevenue =
					orderDAO.getTotalRevenue();

			req.setAttribute(
					"totalUsers",
					totalUsers);

			req.setAttribute(
					"totalRestaurants",
					totalRestaurants);

			req.setAttribute(
					"totalOrders",
					totalOrders);

			req.setAttribute(
					"deliveredOrders",
					orderDAO.getDeliveredOrders());

			req.setAttribute(
					"pendingOrders",
					orderDAO.getPendingOrders());

			req.setAttribute(
					"totalRevenue",
					totalRevenue);

			req.setAttribute(
					"adminName",
					admin.getUsername());

			req.setAttribute(
					"topRestaurant",
					restaurantDAO.getMostOrderedRestaurant());

			req.setAttribute(
					"bestRestaurant",
					restaurantDAO.getHighestRatedRestaurant());

			req.setAttribute(
					"revenueRestaurant",
					restaurantDAO.getHighestRevenueRestaurant());

			req.setAttribute(
					"cancelledOrders",
					orderDAO.getCancelledOrders());

			req.getRequestDispatcher(
					"/adminDashboard.jsp")
			.forward(req, resp);

		}
		catch (Exception e) {

			e.printStackTrace();

			req.setAttribute(
					"errorMessage",
					"Unable to load dashboard.");

			req.getRequestDispatcher(
					"/error.jsp")
			.forward(req, resp);
		}
	}
}