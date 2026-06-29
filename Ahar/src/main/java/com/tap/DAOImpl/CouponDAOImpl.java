package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.CouponDAO;
import com.tap.model.Coupon;
import com.tap.utility.DBConnection;

public class CouponDAOImpl implements CouponDAO {

	private static final String GET_BY_CODE =
			"SELECT *\r\n"
					+ "FROM coupon\r\n"
					+ "WHERE couponCode=?\r\n"
					+ "AND isActive=1\r\n"
					+ "AND expiryDate>=CURDATE()";

	private static final String GET_ALL =
			"SELECT * FROM coupon ORDER BY couponId";

	private static final String INSERT =
			"INSERT INTO coupon(couponCode,discountPercent,minAmount,isActive) VALUES(?,?,?,?)";

	private static final String UPDATE =
			"UPDATE coupon SET couponCode=?,discountPercent=?,minAmount=?,isActive=? WHERE couponId=?";

	private static final String DELETE =
			"DELETE FROM coupon WHERE couponId=?";

	@Override
	public Coupon getCouponByCode(String couponCode) {

		Coupon coupon = null;

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(GET_BY_CODE);
				) {

			pstmt.setString(1, couponCode);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				coupon = new Coupon();

				coupon.setCouponId(rs.getInt("couponId"));
				coupon.setCouponCode(rs.getString("couponCode"));
				coupon.setDiscountPercent(rs.getDouble("discountPercent"));
				coupon.setMinAmount(rs.getDouble("minAmount"));
				coupon.setActive(rs.getBoolean("isActive"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coupon;
	}

	@Override
	public List<Coupon> getAllCoupons() {

		List<Coupon> list = new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(GET_ALL);

				ResultSet rs = pstmt.executeQuery();
				) {

			while (rs.next()) {

				Coupon coupon = new Coupon();

				coupon.setCouponId(rs.getInt("couponId"));
				coupon.setCouponCode(rs.getString("couponCode"));
				coupon.setDiscountPercent(rs.getDouble("discountPercent"));
				coupon.setMinAmount(rs.getDouble("minAmount"));
				coupon.setActive(rs.getBoolean("isActive"));

				list.add(coupon);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void addCoupon(Coupon coupon) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(INSERT);
				) {

			pstmt.setString(1, coupon.getCouponCode());
			pstmt.setDouble(2, coupon.getDiscountPercent());
			pstmt.setDouble(3, coupon.getMinAmount());
			pstmt.setBoolean(4, coupon.isActive());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(UPDATE);
				) {

			pstmt.setString(1, coupon.getCouponCode());
			pstmt.setDouble(2, coupon.getDiscountPercent());
			pstmt.setDouble(3, coupon.getMinAmount());
			pstmt.setBoolean(4, coupon.isActive());
			pstmt.setInt(5, coupon.getCouponId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCoupon(int couponId) {

		try (
				Connection con = DBConnection.getConnection();

				PreparedStatement pstmt =
						con.prepareStatement(DELETE);
				) {

			pstmt.setInt(1, couponId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Coupon> searchCoupon(String keyword) {

		List<Coupon> list =
				new ArrayList<>();

		String sql =
				"SELECT * FROM coupon WHERE couponCode LIKE ?";

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement ps =
						con.prepareStatement(sql);
				) {

			ps.setString(1, "%" + keyword + "%");

			ResultSet rs =
					ps.executeQuery();

			while (rs.next()) {

				Coupon coupon =
						new Coupon();

				coupon.setCouponId(rs.getInt("couponId"));
				coupon.setCouponCode(rs.getString("couponCode"));
				coupon.setDiscountPercent(rs.getDouble("discountPercent"));
				coupon.setMinAmount(rs.getDouble("minAmount"));
				coupon.setActive(rs.getBoolean("isActive"));

				list.add(coupon);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void changeStatus(int couponId,
			boolean status) {

		String sql =
				"UPDATE coupon SET isActive=? WHERE couponId=?";

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement ps =
						con.prepareStatement(sql);
				) {

			ps.setBoolean(1, status);
			ps.setInt(2, couponId);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}