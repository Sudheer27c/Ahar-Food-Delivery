package com.tap.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session = req.getSession();

		try {

			RestaurantDAOImpl dao = new RestaurantDAOImpl();

			String keyword = req.getParameter("keyword");
			String sort = req.getParameter("sort");

			List<Restaurant> restaurants;

			if (keyword != null && !keyword.trim().isEmpty()) {

				restaurants = dao.searchRestaurant(keyword);

			}
			else if ("rating".equalsIgnoreCase(sort)) {

				restaurants = dao.getRestaurantsByRating();

			}
			else if ("fast".equalsIgnoreCase(sort)) {

				restaurants = dao.getFastDeliveryRestaurants();

			}
			else if ("offers".equalsIgnoreCase(sort)) {

				restaurants = dao.getOfferRestaurants();

			}
			else {

				restaurants = dao.getAllRestaurants();
			}

			req.setAttribute("allRestaurants", restaurants);
			req.setAttribute("keyword", keyword);
			req.setAttribute("sort", sort);

			// Restaurant Count
			req.setAttribute("restaurantCount", restaurants.size());

			// Cart Count
			int cartCount = 0;

			Map<?, ?> cart =
					(Map<?, ?>) session.getAttribute("cart");

			if (cart != null) {

				cartCount = cart.size();
			}

			req.setAttribute("cartCount", cartCount);

			RequestDispatcher rd =
					req.getRequestDispatcher("/home.jsp");

			rd.forward(req, resp);

		}
		catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error",
					"Unable to load restaurants.");

			req.setAttribute("allRestaurants",
					new java.util.ArrayList<Restaurant>());

			req.getRequestDispatcher("/home.jsp")
			.forward(req, resp);
		}
	}
}