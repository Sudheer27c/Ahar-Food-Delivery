package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateRestaurant")
public class UpdateRestaurantServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        Restaurant restaurant =
                new Restaurant();

        restaurant.setRestaurantId(
                Integer.parseInt(
                        req.getParameter(
                                "restaurantId")));

        restaurant.setName(
                req.getParameter("name"));

        restaurant.setCuisineType(
                req.getParameter(
                        "cuisineType"));

        restaurant.setDeliveryTime(
                Integer.parseInt(
                        req.getParameter(
                                "deliveryTime")));

        restaurant.setAddress(
                req.getParameter(
                        "address"));

        restaurant.setRating(
                Double.parseDouble(
                        req.getParameter(
                                "rating")));

        restaurant.setImagePath(
                req.getParameter(
                        "imagePath"));

        restaurant.setAdminUserId(1);

        restaurant.setActive(true);

        RestaurantDAOImpl dao =
                new RestaurantDAOImpl();

        dao.updateRestaurant(
                restaurant);

        resp.sendRedirect(
                "restaurantManagement");
    }
}