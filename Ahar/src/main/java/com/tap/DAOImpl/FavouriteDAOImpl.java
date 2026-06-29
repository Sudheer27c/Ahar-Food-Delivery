package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.FavouriteDAO;
import com.tap.model.Favourite;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class FavouriteDAOImpl implements FavouriteDAO {

	private static final String INSERT_FAVOURITE =
			"INSERT INTO favourite(userId, restaurantId) VALUES(?,?)";

	private static final String CHECK_FAVOURITE =
			"SELECT * FROM favourite WHERE userId=? AND restaurantId=?";

	private static final String DELETE_FAVOURITE =
			"DELETE FROM favourite WHERE userId=? AND restaurantId=?";

	private static final String GET_USER_FAVOURITES =
			"SELECT * FROM favourite WHERE userId=?";

	// ==========================================
	// ADD FAVOURITE
	// ==========================================

	@Override
	public void addFavourite(Favourite favourite) {

		try (
				Connection con = DBConnection.getConnection();
				) {

			PreparedStatement checkStmt =
					con.prepareStatement(CHECK_FAVOURITE);

			checkStmt.setInt(1, favourite.getUserId());
			checkStmt.setInt(2, favourite.getRestaurantId());

			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {

				System.out.println("Restaurant already in favourites");
				return;
			}

			PreparedStatement stmt =
					con.prepareStatement(INSERT_FAVOURITE);

			stmt.setInt(1, favourite.getUserId());
			stmt.setInt(2, favourite.getRestaurantId());

			int result = stmt.executeUpdate();

			System.out.println(result + " Favourite Added");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ==========================================
	// REMOVE FAVOURITE
	// ==========================================

	@Override
	public void removeFavourite(int userId,
			int restaurantId) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(DELETE_FAVOURITE);
				) {

			stmt.setInt(1, userId);
			stmt.setInt(2, restaurantId);

			int result = stmt.executeUpdate();

			System.out.println(result + " Favourite Removed");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ==========================================
	// GET FAVOURITE RESTAURANTS
	// ==========================================

	public List<Restaurant> getFavouriteRestaurants(int userId) {

		List<Restaurant> restaurants =
				new ArrayList<>();

		String query =
				"SELECT DISTINCT r.* " +
						"FROM favourite f " +
						"INNER JOIN restaurant r " +
						"ON f.restaurantId = r.restaurantId " +
						"WHERE f.userId=?";

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(query);
				) {

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Restaurant r = new Restaurant();

				r.setRestaurantId(
						rs.getInt("restaurantId"));

				r.setName(
						rs.getString("name"));

				r.setCuisineType(
						rs.getString("cuisineType"));

				r.setDeliveryTime(
						rs.getInt("deliveryTime"));

				r.setAddress(
						rs.getString("address"));

				r.setAdminUserId(
						rs.getInt("adminUserId"));

				r.setRating(
						rs.getDouble("rating"));

				r.setActive(
						rs.getBoolean("isActive"));

				r.setImagePath(
						rs.getString("imagePath"));

				restaurants.add(r);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return restaurants;
	}

	// ==========================================
	// GET FAVOURITE IDS
	// ==========================================

	@Override
	public List<Favourite> getFavouritesByUser(int userId) {

		List<Favourite> favourites =
				new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(GET_USER_FAVOURITES);
				) {

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Favourite favourite =
						new Favourite();

				favourite.setFavouriteId(
						rs.getInt("favouriteId"));

				favourite.setUserId(
						rs.getInt("userId"));

				favourite.setRestaurantId(
						rs.getInt("restaurantId"));

				favourites.add(favourite);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return favourites;
	}
}