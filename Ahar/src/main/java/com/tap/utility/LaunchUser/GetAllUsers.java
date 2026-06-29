package com.tap.utility.LaunchUser;


import java.util.List;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

public class GetAllUsers {

	public static void main(String[] args) {

		UserDAOImpl udao = new UserDAOImpl();

		List<User> users = udao.getAllUsers();

		if (users.isEmpty()) {

			System.out.println("No Users Found");

		} else {

			for (User user : users) {
				System.out.println(user);
			}
		}
	}
}
