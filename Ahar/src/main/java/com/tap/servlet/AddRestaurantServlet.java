package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addRestaurant")
public class AddRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session =
                req.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            resp.sendRedirect("login.jsp");
            return;
        }

        Restaurant restaurant =
                new Restaurant();

        restaurant.setName(
                req.getParameter("name"));

        restaurant.setCuisineType(
                req.getParameter("cuisineType"));

        restaurant.setDeliveryTime(
                Integer.parseInt(
                        req.getParameter("deliveryTime")));

        restaurant.setAddress(
                req.getParameter("address"));

        restaurant.setRating(
                Double.parseDouble(
                        req.getParameter("rating")));

        restaurant.setImagePath(
                req.getParameter("imagePath"));

        restaurant.setAdminUserId(1);

        restaurant.setActive(true);

        RestaurantDAOImpl dao =
                new RestaurantDAOImpl();

        dao.addRestaurant(
                restaurant);

        resp.sendRedirect(
                "restaurantManagement");
    }
}