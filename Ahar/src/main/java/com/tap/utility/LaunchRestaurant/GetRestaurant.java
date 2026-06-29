package com.tap.utility.LaunchRestaurant;

import java.util.Scanner;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

public class GetRestaurant {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Restaurant ID:");

        int restaurantId = sc.nextInt();

        RestaurantDAOImpl rdao =
                new RestaurantDAOImpl();

        Restaurant restaurant =
                rdao.getRestaurant(restaurantId);

        if (restaurant != null) {

            System.out.println(restaurant);

        } else {

            System.out.println("Restaurant Not Found");
        }

        sc.close();
    }
}