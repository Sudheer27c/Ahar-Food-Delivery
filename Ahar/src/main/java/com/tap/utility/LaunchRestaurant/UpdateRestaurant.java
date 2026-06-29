package com.tap.utility.LaunchRestaurant;

import java.util.Scanner;

import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

public class UpdateRestaurant {


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Restaurant ID:");
		int restaurantId = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter Restaurant Name:");
		String name = sc.nextLine();

		System.out.println("Enter Cuisine Type:");
		String cuisineType = sc.nextLine();

		System.out.println("Enter Delivery Time:");
		int deliveryTime = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter Address:");
		String address = sc.nextLine();

		System.out.println("Enter Admin User ID:");
		int adminUserId = sc.nextInt();

		System.out.println("Enter Rating:");
		double rating = sc.nextDouble();

		System.out.println("Is Active (true/false):");
		boolean isActive = sc.nextBoolean();
		sc.nextLine();

		System.out.println("Enter Image Path:");
		String imagePath = sc.nextLine();

		Restaurant restaurant =
				new Restaurant(
						restaurantId,
						name,
						cuisineType,
						deliveryTime,
						address,
						adminUserId,
						rating,
						isActive,
						imagePath
						);

		RestaurantDAOImpl rdao =
				new RestaurantDAOImpl();

		rdao.updateRestaurant(restaurant);

		System.out.println(
				"Restaurant Updated Successfully");

		sc.close();
	}


}
