package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.DAOImpl.ReviewDAOImpl;
import com.tap.model.Restaurant;
import com.tap.model.Review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/restaurantReviews")
public class RestaurantReviewsServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		int restaurantId =
				Integer.parseInt(
						req.getParameter("restaurantId"));

		ReviewDAOImpl reviewDAO =
				new ReviewDAOImpl();

		RestaurantDAOImpl restaurantDAO =
				new RestaurantDAOImpl();

		List<Review> reviews =
				reviewDAO.getReviewsByRestaurantId(
						restaurantId);

		double averageRating =
				reviewDAO.getAverageRating(
						restaurantId);

		Restaurant restaurant =
				restaurantDAO.getRestaurant(
						restaurantId);

		req.setAttribute(
				"reviews",
				reviews);

		req.setAttribute(
				"averageRating",
				averageRating);

		req.setAttribute(
				"restaurant",
				restaurant);

		req.getRequestDispatcher(
				"/restaurantReviews.jsp")
		.forward(req, resp);
	}
}