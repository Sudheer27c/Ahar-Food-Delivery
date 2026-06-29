package com.tap.DAO;

import java.util.List;
import com.tap.model.Review;

public interface ReviewDAO {

	void addReview(Review review);

	List<Review> getReviewsByRestaurantId(
			int restaurantId);

	double getAverageRating(
			int restaurantId);

	void deleteReview(
			int reviewId);
}