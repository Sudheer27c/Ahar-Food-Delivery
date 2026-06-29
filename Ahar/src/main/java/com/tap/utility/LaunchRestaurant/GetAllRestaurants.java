package com.tap.utility.LaunchRestaurant;

import java.util.List;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

public class GetAllRestaurants {

    public static void main(String[] args) {

        RestaurantDAOImpl rdao =
                new RestaurantDAOImpl();

        List<Restaurant> restaurantList =
                rdao.getAllRestaurants();

        if (restaurantList.isEmpty()) {

            System.out.println("No Restaurants Found");

        } else {

            for (Restaurant restaurant : restaurantList) {

                System.out.println(restaurant);
            }
        }
    }
}