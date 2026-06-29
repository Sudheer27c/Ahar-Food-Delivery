package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/searchRestaurant")
public class SearchRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		String keyword =
				req.getParameter("keyword");

		RestaurantDAOImpl dao =
				new RestaurantDAOImpl();

		List<Restaurant> restaurants =
				dao.searchRestaurant(
						keyword);

		req.setAttribute(
				"allRestaurants",
				restaurants);

		req.getRequestDispatcher(
				"home.jsp")
		.forward(req, resp);
	}
}