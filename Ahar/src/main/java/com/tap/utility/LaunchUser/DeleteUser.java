package com.tap.utility.LaunchUser;

import java.util.Scanner;

import com.tap.DAOImpl.UserDAOImpl;

public class DeleteUser {

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);

		System.out.println("Enter User ID:");

		int userId = sc.nextInt();

		UserDAOImpl udao = new UserDAOImpl();

		udao.deleteUser(userId);

		System.out.println("User Deleted Successfully");

		sc.close();

	}

}
