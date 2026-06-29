package com.tap.utility.LaunchUser;

import java.util.Scanner;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

public class GetUser {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter User ID:");

		int userId = sc.nextInt();

		UserDAOImpl udao = new UserDAOImpl();

		User user = udao.getUser(userId);

		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("User Not Found");
		}

		sc.close();
	}
}

