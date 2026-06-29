package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String INSERT_QUERY =
			"INSERT INTO orderitem(orderId,menuId,quantity,itemTotal) VALUES(?,?,?,?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM orderitem WHERE orderItemId=?";

	private static final String UPDATE_QUERY =
			"UPDATE orderitem SET orderId=?, menuId=?, quantity=?, itemTotal=? WHERE orderItemId=?";

	private static final String DELETE_QUERY =
			"DELETE FROM orderitem WHERE orderItemId=?";

	private static final String GET_ALL_ITEMS =
			"SELECT * FROM orderitem";

	private static final String GET_BY_ORDER_ID =
			"SELECT oi.*,m.ItemName,m.Price,m.ImagePath " +
					"FROM orderitem oi " +
					"JOIN menu m ON oi.menuId=m.MenuID " +
					"WHERE oi.orderId=?";

	@Override
	public void addOrderItem(OrderItem item) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								INSERT_QUERY);
				) {

			stmt.setInt(1, item.getOrderId());
			stmt.setInt(2, item.getMenuId());
			stmt.setInt(3, item.getQuantity());
			stmt.setDouble(4, item.getItemTotal());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {

		OrderItem item = null;

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								SELECT_QUERY);
				) {

			stmt.setInt(1, orderItemId);

			try (ResultSet rs =
					stmt.executeQuery()) {

				if (rs.next()) {

					item = new OrderItem(
							rs.getInt("OrderItemID"),
							rs.getInt("OrderID"),
							rs.getInt("MenuID"),
							rs.getInt("Quantity"),
							rs.getDouble("ItemTotal"));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return item;
	}

	@Override
	public void updateOrderItem(OrderItem item) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								UPDATE_QUERY);
				) {

			stmt.setInt(1, item.getOrderId());
			stmt.setInt(2, item.getMenuId());
			stmt.setInt(3, item.getQuantity());
			stmt.setDouble(4, item.getItemTotal());
			stmt.setInt(5, item.getOrderItemId());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemId) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								DELETE_QUERY);
				) {

			stmt.setInt(1, orderItemId);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		List<OrderItem> itemList =
				new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								GET_ALL_ITEMS);

				ResultSet rs =
						stmt.executeQuery();
				) {

			while (rs.next()) {

				itemList.add(
						new OrderItem(
								rs.getInt("OrderItemID"),
								rs.getInt("OrderID"),
								rs.getInt("MenuID"),
								rs.getInt("Quantity"),
								rs.getDouble("ItemTotal")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return itemList;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {

		List<OrderItem> itemList =
				new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								GET_BY_ORDER_ID);
				) {

			stmt.setInt(1, orderId);

			try (ResultSet rs =
					stmt.executeQuery()) {

				while (rs.next()) {

					OrderItem item = new OrderItem(
							rs.getInt("OrderItemID"),
							rs.getInt("OrderID"),
							rs.getInt("MenuID"),
							rs.getInt("Quantity"),
							rs.getDouble("ItemTotal"));

					item.setItemName(
							rs.getString("ItemName"));

					item.setPrice(
							rs.getDouble("Price"));

					item.setImagePath(
							rs.getString("ImagePath"));

					itemList.add(item);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return itemList;
	}
	public List<OrderItem> getOrderItems(int orderId) {

		List<OrderItem> items = new ArrayList<>();

		String query =
				"SELECT oi.OrderItemId, oi.OrderId, oi.MenuId, " +
						"oi.Quantity, oi.ItemTotal, " +
						"m.ItemName, m.Price, m.ImagePath " +
						"FROM orderitem oi " +
						"JOIN menu m ON oi.MenuId = m.MenuId " +
						"WHERE oi.OrderId=?";

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);
				) {

			pstmt.setInt(1, orderId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				OrderItem item = new OrderItem();

				item.setOrderItemId(
						rs.getInt("OrderItemId"));

				item.setOrderId(
						rs.getInt("OrderId"));

				item.setMenuId(
						rs.getInt("MenuId"));

				item.setQuantity(
						rs.getInt("Quantity"));

				item.setItemTotal(
						rs.getDouble("ItemTotal"));

				item.setItemName(
						rs.getString("ItemName"));

				item.setPrice(
						rs.getDouble("Price"));

				item.setImagePath(
						rs.getString("ImagePath"));

				items.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return items;
	}

}
