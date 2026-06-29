package com.tap.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderTableDAO;
import com.tap.model.OrderTable;
import com.tap.utility.DBConnection;

public class OrderTableDAOImpl implements OrderTableDAO {

	private static final String INSERT =
			"INSERT INTO ordertable(UserID,OrderDate,TotalAmount,Status,PaymentMethod,RestaurantId) VALUES(?,?,?,?,?,?)";

	private static final String GET_ORDER =
			"SELECT * FROM ordertable WHERE OrderID=?";

	private static final String GET_ALL =
			"SELECT * FROM ordertable ORDER BY OrderDate DESC";

	private static final String GET_BY_USER =
			"SELECT * FROM ordertable WHERE UserID=? ORDER BY OrderDate DESC";

	private static final String UPDATE =
			"UPDATE ordertable SET Status=?, PaymentMethod=? WHERE OrderID=?";

	private static final String DELETE =
			"DELETE FROM ordertable WHERE OrderID=?";

	public int getCancelledOrders() {

		int count = 0;

		String query =
				"SELECT COUNT(*) total FROM ordertable WHERE Status='CANCELLED'";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getPendingOrders() {

		int count = 0;

		String query =
				"SELECT COUNT(*) total FROM ordertable WHERE Status='PENDING'";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getDeliveredOrders() {

		int count = 0;

		String query =
				"SELECT COUNT(*) total FROM ordertable WHERE Status='DELIVERED'";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
	public void updateOrderStatus(
			int orderId,
			String status) {

		String query =
				"UPDATE ordertable SET Status=? WHERE OrderID=?";

		try(
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);
				){

			pstmt.setString(1, status);
			pstmt.setInt(2, orderId);

			int result =
					pstmt.executeUpdate();

			System.out.println(
					"Order Updated Successfully");

			System.out.println(
					"Order ID : " + orderId);

			System.out.println(
					"Status : " + status);

			System.out.println(
					"Rows Updated : " + result);

		}
		catch(Exception e) {

			e.printStackTrace();
		}
	}

	public double getTotalRevenue() {

		double revenue = 0;

		String query =
				"SELECT IFNULL(SUM(TotalAmount),0) AS revenue FROM ordertable";

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);

				ResultSet rs =
						pstmt.executeQuery()) {

			if (rs.next()) {

				revenue = rs.getDouble("revenue");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return revenue;
	}
	public int getTotalOrders() {

		int count = 0;

		String query =
				"SELECT COUNT(*) AS total FROM ordertable";

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(query);

				ResultSet rs =
						pstmt.executeQuery()) {

			if (rs.next()) {

				count = rs.getInt("total");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}
	@Override
	public int addOrder(OrderTable order) {

		int generatedId = 0;

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								INSERT,
								Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, order.getUserId());
			pstmt.setTimestamp(2, order.getOrderDate());
			pstmt.setDouble(3, order.getTotalAmount());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getPaymentMethod());
			pstmt.setInt(6, order.getRestaurantId());

			int result = pstmt.executeUpdate();

			if (result > 0) {

				ResultSet rs =
						pstmt.getGeneratedKeys();

				if (rs.next()) {
					generatedId =
							rs.getInt(1);

					System.out.println(
							"Order Inserted ID = "
									+ generatedId);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return generatedId;
	}
	@Override
	public OrderTable getOrder(int orderId) {

		OrderTable order = null;

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								GET_ORDER);
				) {

			pstmt.setInt(
					1,
					orderId);

			try (
					ResultSet rs =
					pstmt.executeQuery();
					) {

				if (rs.next()) {

					order =
							new OrderTable(
									rs.getInt("OrderID"),
									rs.getInt("UserID"),
									rs.getTimestamp("OrderDate"),
									rs.getDouble("TotalAmount"),
									rs.getString("Status"),
									rs.getString("PaymentMethod"),
									rs.getInt("RestaurantId"));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return order;
	}

	@Override
	public List<OrderTable> getAllOrders() {

		List<OrderTable> orders =
				new ArrayList<>();

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								GET_ALL);

				ResultSet rs =
						pstmt.executeQuery();
				) {

			while (rs.next()) {

				orders.add(
						new OrderTable(
								rs.getInt("OrderID"),
								rs.getInt("UserID"),
								rs.getTimestamp("OrderDate"),
								rs.getDouble("TotalAmount"),
								rs.getString("Status"),
								rs.getString("PaymentMethod"),
								rs.getInt("RestaurantId")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public List<OrderTable> getOrdersByUserId(int userId) {

		List<OrderTable> orders =
				new ArrayList<>();

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								GET_BY_USER);
				) {

			pstmt.setInt(
					1,
					userId);

			try (
					ResultSet rs =
					pstmt.executeQuery();
					) {

				while (rs.next()) {

					orders.add(
							new OrderTable(
									rs.getInt("OrderID"),
									rs.getInt("UserID"),
									rs.getTimestamp("OrderDate"),
									rs.getDouble("TotalAmount"),
									rs.getString("Status"),
									rs.getString("PaymentMethod"),
									rs.getInt("RestaurantId")));
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public void updateOrder(OrderTable order) {

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								UPDATE);
				) {

			pstmt.setString(
					1,
					order.getStatus());

			pstmt.setString(
					2,
					order.getPaymentMethod());

			pstmt.setInt(
					3,
					order.getOrderId());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderId) {

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(
								DELETE);
				) {

			pstmt.setInt(
					1,
					orderId);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
