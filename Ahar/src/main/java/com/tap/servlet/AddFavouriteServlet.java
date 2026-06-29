package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.FavouriteDAOImpl;
import com.tap.model.Favourite;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addFavourite")
public class AddFavouriteServlet extends HttpServlet {

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

		int restaurantId =
				Integer.parseInt(
						req.getParameter("restaurantId"));

		Favourite favourite =
				new Favourite();

		favourite.setUserId(
				user.getUserId());

		favourite.setRestaurantId(
				restaurantId);

		FavouriteDAOImpl dao =
				new FavouriteDAOImpl();

		dao.addFavourite(favourite);

		resp.sendRedirect("favourites");
	}
}