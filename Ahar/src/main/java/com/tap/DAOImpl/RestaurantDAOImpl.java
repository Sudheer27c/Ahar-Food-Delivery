package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String INSERT_QUERY =
			"INSERT INTO restaurant(name,cuisineType,deliveryTime,address,adminUserId,rating,isActive,imagePath) VALUES(?,?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM restaurant WHERE restaurantId=?";

	private static final String UPDATE_QUERY =
			"UPDATE restaurant SET name=?, cuisineType=?, deliveryTime=?, address=?, adminUserId=?, rating=?, isActive=?, imagePath=? WHERE restaurantId=?";

	private static final String DELETE_QUERY =
			"DELETE FROM restaurant WHERE restaurantId=?";

	private static final String GET_ALL_QUERY =
			"SELECT * FROM restaurant WHERE isActive = 1 ORDER BY rating DESC";

	private static final String SEARCH_RESTAURANT =
			"SELECT * FROM restaurant " +
					"WHERE isActive = 1 " +
					"AND (name LIKE ? OR cuisineType LIKE ? OR address LIKE ?) " +
					"ORDER BY rating DESC";

	private static final String GET_BY_RATING =
			"SELECT * FROM restaurant WHERE isActive=1 ORDER BY rating DESC";

	private static final String GET_FAST_DELIVERY =
			"SELECT * FROM restaurant WHERE isActive=1 ORDER BY deliveryTime ASC";

	private static final String GET_OFFERS =
			"SELECT * FROM restaurant WHERE isActive=1 ORDER BY rating DESC";

	public String getHighestRevenueRestaurant() {

		String query =
				"SELECT r.name " +
						"FROM restaurant r " +
						"JOIN ordertable o " +
						"ON r.restaurantId=o.restaurantId " +
						"GROUP BY r.restaurantId " +
						"ORDER BY SUM(o.TotalAmount) DESC LIMIT 1";

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);

				ResultSet rs =
						pstmt.executeQuery();
				){

			if(rs.next()) {
				return rs.getString("name");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return "N/A";
	}

	public String getHighestRatedRestaurant() {

		String query =
				"SELECT name " +
						"FROM restaurant " +
						"ORDER BY rating DESC LIMIT 1";

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);

				ResultSet rs =
						pstmt.executeQuery();
				){

			if(rs.next()) {
				return rs.getString("name");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return "N/A";
	}

	public String getMostOrderedRestaurant() {

		String query =
				"SELECT r.name " +
						"FROM restaurant r " +
						"JOIN ordertable o " +
						"ON r.restaurantId=o.restaurantId " +
						"GROUP BY r.restaurantId " +
						"ORDER BY COUNT(*) DESC LIMIT 1";

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);

				ResultSet rs =
						pstmt.executeQuery();
				){

			if(rs.next()) {
				return rs.getString("name");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return "N/A";
	}

	public String getRestaurantName(int restaurantId) {

		String query =
				"SELECT name FROM restaurant WHERE restaurantId=?";

		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				){

			pstmt.setInt(1, restaurantId);

			try(ResultSet rs = pstmt.executeQuery()) {

				if(rs.next()) {

					return rs.getString("name");
				}
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return "Unknown Restaurant";
	}
	public void updateRestaurantRating(int restaurantId) {

		String query =
				"UPDATE restaurant " +
						"SET rating=(" +
						"SELECT AVG(rating) FROM review WHERE restaurantId=?" +
						") WHERE restaurantId=?";

		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				){

			pstmt.setInt(1, restaurantId);
			pstmt.setInt(2, restaurantId);

			pstmt.executeUpdate();

		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	public int getRestaurantCount() {

		String query =
				"SELECT COUNT(*) total FROM restaurant WHERE isActive=1";

		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				ResultSet rs =
						pstmt.executeQuery();
				){

			if(rs.next()) {

				return rs.getInt("total");
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return 0;
	}
	@Override
	public void addRestaurant(Restaurant restaurant) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								INSERT_QUERY);
				){

			stmt.setString(1,
					restaurant.getName());

			stmt.setString(2,
					restaurant.getCuisineType());

			stmt.setInt(3,
					restaurant.getDeliveryTime());

			stmt.setString(4,
					restaurant.getAddress());

			stmt.setInt(5,
					restaurant.getAdminUserId());

			stmt.setDouble(6,
					restaurant.getRating());

			stmt.setBoolean(7,
					restaurant.isActive());

			stmt.setString(8,
					restaurant.getImagePath());

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {

		Restaurant restaurant = null;

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								SELECT_QUERY);
				){

			stmt.setInt(1, restaurantId);

			try(ResultSet result = stmt.executeQuery()) {

				if(result.next()) {

					restaurant = new Restaurant(
							result.getInt("restaurantId"),
							result.getString("name"),
							result.getString("cuisineType"),
							result.getInt("deliveryTime"),
							result.getString("address"),
							result.getInt("adminUserId"),
							result.getDouble("rating"),
							result.getBoolean("isActive"),
							result.getString("imagePath"));
				}
			}

		}
		catch(SQLException e) {

			e.printStackTrace();
		}

		return restaurant;
	}
	@Override
	public void updateRestaurant(
			Restaurant restaurant) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								UPDATE_QUERY);
				){

			stmt.setString(1,
					restaurant.getName());

			stmt.setString(2,
					restaurant.getCuisineType());

			stmt.setInt(3,
					restaurant.getDeliveryTime());

			stmt.setString(4,
					restaurant.getAddress());

			stmt.setInt(5,
					restaurant.getAdminUserId());

			stmt.setDouble(6,
					restaurant.getRating());

			stmt.setBoolean(7,
					restaurant.isActive());

			stmt.setString(8,
					restaurant.getImagePath());

			stmt.setInt(9,
					restaurant.getRestaurantId());


			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(
			int restaurantId) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								DELETE_QUERY);
				){

			stmt.setInt(1,
					restaurantId);


		

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> restaurantList =
				new ArrayList<>();

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								GET_ALL_QUERY);
				){

			ResultSet result =
					stmt.executeQuery();

			while(result.next()) {

				Restaurant restaurant =
						new Restaurant(
								result.getInt("restaurantId"),
								result.getString("name"),
								result.getString("cuisineType"),
								result.getInt("deliveryTime"),
								result.getString("address"),
								result.getInt("adminUserId"),
								result.getDouble("rating"),
								result.getBoolean("isActive"),
								result.getString("imagePath")
								);

				restaurantList.add(
						restaurant);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}

	@Override
	public List<Restaurant> searchRestaurant(
			String keyword) {

		List<Restaurant> restaurantList =
				new ArrayList<>();

		if(keyword == null ||
				keyword.trim().isEmpty()) {

			return getAllRestaurants();
		}

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								SEARCH_RESTAURANT);
				){

			stmt.setString(
					1,
					"%" + keyword + "%");

			stmt.setString(
					2,
					"%" + keyword + "%");

			stmt.setString(
					3,
					"%" + keyword + "%");

			ResultSet result =
					stmt.executeQuery();

			while(result.next()) {

				Restaurant restaurant =
						new Restaurant(
								result.getInt("restaurantId"),
								result.getString("name"),
								result.getString("cuisineType"),
								result.getInt("deliveryTime"),
								result.getString("address"),
								result.getInt("adminUserId"),
								result.getDouble("rating"),
								result.getBoolean("isActive"),
								result.getString("imagePath")
								);

				restaurantList.add(
						restaurant);
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}
	private List<Restaurant> getRestaurants(String query) {

		List<Restaurant> list = new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				) {

			while (rs.next()) {

				Restaurant restaurant = new Restaurant(
						rs.getInt("restaurantId"),
						rs.getString("name"),
						rs.getString("cuisineType"),
						rs.getInt("deliveryTime"),
						rs.getString("address"),
						rs.getInt("adminUserId"),
						rs.getDouble("rating"),
						rs.getBoolean("isActive"),
						rs.getString("imagePath"));

				list.add(restaurant);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	@Override
	public List<Restaurant> getRestaurantsByRating() {

		return getRestaurants(GET_BY_RATING);
	}

	@Override
	public List<Restaurant> getFastDeliveryRestaurants() {

		return getRestaurants(GET_FAST_DELIVERY);
	}

	@Override
	public List<Restaurant> getOfferRestaurants() {

		return getRestaurants(GET_OFFERS);
	}
}

