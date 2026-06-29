package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.RestaurantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteRestaurant")
public class DeleteRestaurantServlet extends HttpServlet {

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

        dao.deleteRestaurant(
                restaurantId);

        resp.sendRedirect(
                "restaurantManagement");
    }
}