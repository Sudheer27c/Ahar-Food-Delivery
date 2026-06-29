package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.FavouriteDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/removeFavourite")
public class RemoveFavouriteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		User user =
				(User) session.getAttribute(
						"loggedInUser");

		int restaurantId =
				Integer.parseInt(
						req.getParameter(
								"restaurantId"));

		FavouriteDAOImpl dao =
				new FavouriteDAOImpl();

		dao.removeFavourite(
				user.getUserId(),
				restaurantId);

		resp.sendRedirect("favourites");
	}
}