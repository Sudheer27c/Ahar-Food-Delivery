package com.tap.DAO;

import java.util.List;
import com.tap.model.Restaurant;

public interface RestaurantDAO {

	void addRestaurant(Restaurant restaurant);

	Restaurant getRestaurant(int restaurantId);

	List<Restaurant> getAllRestaurants();

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int restaurantId);

	// Search Restaurant By Name
	List<Restaurant> searchRestaurant(String keyword);
	
	List<Restaurant> getRestaurantsByRating();

	List<Restaurant> getFastDeliveryRestaurants();

	List<Restaurant> getOfferRestaurants();
}