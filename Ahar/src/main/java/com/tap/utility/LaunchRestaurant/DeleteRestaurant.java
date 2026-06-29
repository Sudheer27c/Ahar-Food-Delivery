package com.tap.utility.LaunchRestaurant;

import java.util.Scanner;

import com.tap.DAOImpl.RestaurantDAOImpl;

public class DeleteRestaurant {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Restaurant ID:");

        int restaurantId = sc.nextInt();

        RestaurantDAOImpl rdao =
                new RestaurantDAOImpl();

        rdao.deleteRestaurant(restaurantId);

        System.out.println("Restaurant Deleted Successfully");

        sc.close();
    }
}