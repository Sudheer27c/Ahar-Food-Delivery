package com.tap.DAO;

import java.util.List;

import com.tap.model.User;

public interface UserDAO {

	int addUser(User user);

	User getUser(int userId);

	User getUserByEmail(String email);

	void updateUser(User user);

	void updatePassword(String email, String password);

	void deleteUser(int userId);

	List<User> getAllUsers();

	int getUserCount();
}