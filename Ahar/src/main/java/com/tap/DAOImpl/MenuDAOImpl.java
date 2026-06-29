package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private static final String INSERT_QUERY =
			"INSERT INTO menu(RestaurantId,Itemname,Description,Price,IsAvailable,ImagePath,Rating) VALUES(?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM menu WHERE MenuID=?";

	private static final String UPDATE_QUERY =
			"UPDATE menu SET RestaurantId=?, Itemname=?, Description=?, Price=?, IsAvailable=?, ImagePath=?, Rating=? WHERE MenuID=?";

	private static final String DELETE_QUERY =
			"DELETE FROM menu WHERE MenuID=?";

	private static final String GET_ALL_QUERY =
			"SELECT * FROM menu WHERE IsAvailable=1 ORDER BY Rating DESC";

	private static final String GET_MENU_BY_RESTAURANT =
			"SELECT * FROM menu WHERE RestaurantId=? AND IsAvailable=1 ORDER BY Rating DESC";

	@Override
	public void addMenu(Menu menu) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(INSERT_QUERY);
				){

			stmt.setInt(1, menu.getRestaurantId());
			stmt.setString(2, menu.getItemName());
			stmt.setString(3, menu.getDescription());
			stmt.setDouble(4, menu.getPrice());
			stmt.setBoolean(5, menu.isAvailable());
			stmt.setString(6, menu.getImagePath());
			stmt.setDouble(7, menu.getRating());

			stmt.executeUpdate();

		}
		catch(SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {

		Menu menu = null;

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(SELECT_QUERY);
				){

			stmt.setInt(1, menuId);

			ResultSet rs =
					stmt.executeQuery();

			if(rs.next()) {

				menu =
						new Menu(
								rs.getInt("MenuID"),
								rs.getInt("RestaurantId"),
								rs.getString("Itemname"),
								rs.getString("Description"),
								rs.getDouble("Price"),
								rs.getBoolean("IsAvailable"),
								rs.getString("ImagePath"),
								rs.getDouble("Rating")
								);
			}

		}
		catch(SQLException e) {

			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(UPDATE_QUERY);
				){

			stmt.setInt(1, menu.getRestaurantId());
			stmt.setString(2, menu.getItemName());
			stmt.setString(3, menu.getDescription());
			stmt.setDouble(4, menu.getPrice());
			stmt.setBoolean(5, menu.isAvailable());
			stmt.setString(6, menu.getImagePath());
			stmt.setDouble(7, menu.getRating());
			stmt.setInt(8, menu.getMenuId());

			stmt.executeUpdate();

		}
		catch(SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(DELETE_QUERY);
				){

			stmt.setInt(1, menuId);

			stmt.executeUpdate();

		}
		catch(SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenus() {

		List<Menu> menuList =
				new ArrayList<>();

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(GET_ALL_QUERY);

				ResultSet rs =
						stmt.executeQuery();
				){

			while(rs.next()) {

				Menu menu =
						new Menu(
								rs.getInt("MenuID"),
								rs.getInt("RestaurantId"),
								rs.getString("Itemname"),
								rs.getString("Description"),
								rs.getDouble("Price"),
								rs.getBoolean("IsAvailable"),
								rs.getString("ImagePath"),
								rs.getDouble("Rating")
								);

				menuList.add(menu);
			}

		}
		catch(SQLException e) {

			e.printStackTrace();
		}

		return menuList;
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {

		List<Menu> menuList =
				new ArrayList<>();

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(GET_MENU_BY_RESTAURANT);
				){

			stmt.setInt(1, restaurantId);

			ResultSet rs =
					stmt.executeQuery();

			while(rs.next()) {

				Menu menu =
						new Menu(
								rs.getInt("MenuID"),
								rs.getInt("RestaurantId"),
								rs.getString("Itemname"),
								rs.getString("Description"),
								rs.getDouble("Price"),
								rs.getBoolean("IsAvailable"),
								rs.getString("ImagePath"),
								rs.getDouble("Rating")
								);

				menuList.add(menu);
			}

		}
		catch(SQLException e) {

			e.printStackTrace();
		}

		return menuList;
	}

	public int getMenuCount() {

		int count = 0;

		String query =
				"SELECT COUNT(*) FROM menu";

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(query);

				ResultSet rs =
						stmt.executeQuery();
				){

			if(rs.next()) {

				count =
						rs.getInt(1);
			}

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return count;
	}
}

