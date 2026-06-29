package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/restaurantManagement")
public class RestaurantManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        RestaurantDAOImpl dao =
                new RestaurantDAOImpl();

        List<Restaurant> restaurants =
                dao.getAllRestaurants();

        req.setAttribute(
                "restaurants",
                restaurants);

        req.getRequestDispatcher(
                "/restaurantManagement.jsp")
        .forward(req, resp);
    }
}