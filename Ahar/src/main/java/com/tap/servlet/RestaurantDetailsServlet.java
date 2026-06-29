package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.DAOImpl.ReviewDAOImpl;
import com.tap.model.Restaurant;
import com.tap.model.Review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurantDetails")
public class RestaurantDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		int restaurantId =
				Integer.parseInt(
						req.getParameter("restaurantId"));

		RestaurantDAOImpl restaurantDAO =
				new RestaurantDAOImpl();

		ReviewDAOImpl reviewDAO =
				new ReviewDAOImpl();

		Restaurant restaurant =
				restaurantDAO.getRestaurant(
						restaurantId);

		List<Review> reviews =
				reviewDAO.getReviewsByRestaurantId(
						restaurantId);

		req.setAttribute(
				"restaurant",
				restaurant);

		req.setAttribute(
				"reviews",
				reviews);

		req.getRequestDispatcher(
				"restaurantDetails.jsp")
		.forward(req, resp);
	}
}