package com.tap.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.model.User;
import com.tap.utility.DBConnection;

public class UserDAOImpl implements UserDAO {
	
	private static final String INSERT_QUERY =
			"INSERT INTO user(userName,password,email,address,role,createDate,lastLoginDate) VALUES(?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY =
			"SELECT * FROM user WHERE userId=?";

	private static final String GET_USER_BY_EMAIL =
			"SELECT * FROM user WHERE email=?";

	private static final String UPDATE_QUERY =
			"UPDATE user SET userName=?, password=?, email=?, address=?, role=? WHERE userId=?";

	private static final String DELETE_QUERY =
			"DELETE FROM user WHERE userId=?";

	private static final String GET_ALL_USERS =
			"SELECT * FROM user";

	private static final String UPDATE_PASSWORD =
			"UPDATE user SET password=? WHERE email=?";

	public int getCustomerCount() {

		int count = 0;

		String query =
				"SELECT COUNT(*) total " +
						"FROM user " +
						"WHERE role='customer'";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				ResultSet rs =
						pstmt.executeQuery();
				) {

			if(rs.next()) {

				count =
						rs.getInt("total");
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public int getTodayRegistrations() {

		int count = 0;

		String query =
				"SELECT COUNT(*) total " +
						"FROM user " +
						"WHERE DATE(createDate)=CURDATE()";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				ResultSet rs =
						pstmt.executeQuery();
				) {

			if(rs.next()) {

				count =
						rs.getInt("total");
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public String getLatestUser() {

		String userName = "N/A";

		String query =
				"SELECT userName FROM user ORDER BY createDate DESC LIMIT 1";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt =
						con.prepareStatement(query);
				ResultSet rs =
						pstmt.executeQuery();
				) {

			if(rs.next()) {

				userName =
						rs.getString("userName");
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return userName;
	}

	public boolean emailExists(String email) {

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(GET_USER_BY_EMAIL);
				) {

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public void updateLastLogin(int userId) {

		String query =
				"UPDATE user SET lastLoginDate=? WHERE userId=?";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(query);
				) {

			stmt.setTimestamp(
					1,
					new Timestamp(System.currentTimeMillis()));

			stmt.setInt(2, userId);

			stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public int addUser(User user) {

		int result = 0;

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(INSERT_QUERY);
				) {

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getRole());

			Timestamp now =
					new Timestamp(System.currentTimeMillis());

			stmt.setTimestamp(6, now);
			stmt.setTimestamp(7, now);

			result = stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public User getUserByEmail(String email) {

		User user = null;

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(GET_USER_BY_EMAIL);
				) {

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUser(int userId) {

		User user = null;

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(SELECT_QUERY);
				) {

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void updateUser(User user) {

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(UPDATE_QUERY);
				) {

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getRole());
			stmt.setInt(6, user.getUserId());

			stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Override
	public void deleteUser(int userId) {

		try (
				Connection con =
				DBConnection.getConnection();

				PreparedStatement stmt =
						con.prepareStatement(
								DELETE_QUERY);
				) {

			stmt.setInt(1, userId);

			int result =
					stmt.executeUpdate();

			System.out.println(
					result + " user deleted");

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_USERS);
				ResultSet rs = pstmt.executeQuery();
				) {

			while(rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

				users.add(user);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return users;
	}
	@Override
	public void updatePassword(
			String email,
			String password) {

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(UPDATE_PASSWORD);
				) {

			stmt.setString(1, password);
			stmt.setString(2, email);

			stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Override
	public int getUserCount() {

		int count = 0;

		String query =
				"SELECT COUNT(*) AS total FROM user";

		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(query);
				ResultSet rs =
						stmt.executeQuery();
				) {

			if (rs.next()) {

				count = rs.getInt("total");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

}