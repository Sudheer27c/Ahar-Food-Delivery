package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.ReviewDAO;
import com.tap.model.Review;
import com.tap.utility.DBConnection;

public class ReviewDAOImpl implements ReviewDAO {

	private static final String INSERT =
			"INSERT INTO review(userId,restaurantId,rating,reviewText) VALUES(?,?,?,?)";

	private static final String GET_REVIEWS =
			"SELECT r.*, u.userName " +
					"FROM review r " +
					"JOIN user u ON r.userId = u.userId " +
					"WHERE r.restaurantId=? " +
					"ORDER BY r.reviewDate DESC";
	private static final String AVG_RATING =
			"SELECT AVG(rating) avgRating FROM review WHERE restaurantId=?";

	private static final String DELETE_REVIEW =
			"DELETE FROM review WHERE reviewId=?";

	// ============================
	// ADD REVIEW
	// ============================

	@Override
	public void addReview(Review review) {

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(INSERT);
				) {

			pstmt.setInt(
					1,
					review.getUserId());

			pstmt.setInt(
					2,
					review.getRestaurantId());

			pstmt.setInt(
					3,
					review.getRating());

			pstmt.setString(
					4,
					review.getReviewText());

			int result =
					pstmt.executeUpdate();

			System.out.println(
					result +
					" Review Added Successfully");

			// Update Restaurant Rating

			RestaurantDAOImpl restaurantDAO =
					new RestaurantDAOImpl();

			restaurantDAO.updateRestaurantRating(
					review.getRestaurantId());

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================
	// GET REVIEWS BY RESTAURANT
	// ============================

	@Override
	public List<Review> getReviewsByRestaurantId(
			int restaurantId) {

		List<Review> reviews =
				new ArrayList<>();

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								GET_REVIEWS);
				) {

			pstmt.setInt(
					1,
					restaurantId);

			ResultSet rs =
					pstmt.executeQuery();

			while (rs.next()) {

				Review review =
						new Review(
								rs.getInt("reviewId"),
								rs.getInt("userId"),
								rs.getInt("restaurantId"),
								rs.getInt("rating"),
								rs.getString("reviewText"),
								rs.getTimestamp("reviewDate"));

				// NEW
				review.setUserName(
						rs.getString("userName"));

				reviews.add(review);
			}

		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return reviews;
	}
	// ============================
	// GET AVERAGE RATING
	// ============================

	@Override
	public double getAverageRating(
			int restaurantId) {

		double rating = 0;

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								AVG_RATING);
				) {

			pstmt.setInt(
					1,
					restaurantId);

			ResultSet rs =
					pstmt.executeQuery();

			if (rs.next()) {

				rating =
						rs.getDouble(
								"avgRating");
			}

		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return rating;
	}

	// ============================
	// DELETE REVIEW
	// ============================

	@Override
	public void deleteReview(
			int reviewId) {

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								DELETE_REVIEW);
				) {

			pstmt.setInt(
					1,
					reviewId);

			int result =
					pstmt.executeUpdate();

			System.out.println(
					result +
					" Review Deleted");

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
}