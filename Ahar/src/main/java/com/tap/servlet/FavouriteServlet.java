package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.FavouriteDAOImpl;
import com.tap.model.Restaurant;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/favourites")
public class FavouriteServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if(session == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		User user =
				(User) session.getAttribute(
						"loggedInUser");

		if(user == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		FavouriteDAOImpl dao =
				new FavouriteDAOImpl();

		List<Restaurant> restaurants =
				dao.getFavouriteRestaurants(
						user.getUserId());

		for(Restaurant r : restaurants){

			System.out.println(
					r.getRestaurantId() + " "
							+ r.getName());
		}

		req.setAttribute(
				"favouriteRestaurants",
				restaurants);

		req.getRequestDispatcher(
				"/favourites.jsp")
		.forward(req, resp);
	}
}