package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editRestaurant")
public class EditRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(
                        req.getParameter(
                                "restaurantId"));

        RestaurantDAOImpl dao =
                new RestaurantDAOImpl();

        Restaurant restaurant =
                dao.getRestaurant(
                        restaurantId);

        req.setAttribute(
                "restaurant",
                restaurant);

        req.getRequestDispatcher(
                "/editRestaurant.jsp")
        .forward(req, resp);
    }
}