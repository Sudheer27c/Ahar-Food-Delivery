package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.ReviewDAOImpl;
import com.tap.model.Review;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addReview")
public class AddReviewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if(session == null){
			resp.sendRedirect("login.jsp");
			return;
		}

		User user =
				(User) session.getAttribute("loggedInUser");

		if(user == null){
			resp.sendRedirect("login.jsp");
			return;
		}

		int restaurantId =
				Integer.parseInt(
						req.getParameter("restaurantId"));

		int rating =
				Integer.parseInt(
						req.getParameter("rating"));

		String reviewText =
				req.getParameter("reviewText");

		Review review =
				new Review();

		review.setUserId(
				user.getUserId());

		review.setRestaurantId(
				restaurantId);

		review.setRating(
				rating);

		review.setReviewText(
				reviewText);

		ReviewDAOImpl dao =
				new ReviewDAOImpl();

		dao.addReview(review);

		resp.sendRedirect(
				"restaurantReviews?restaurantId="
						+ restaurantId);
	}
}