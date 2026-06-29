package com.tap.utility.LaunchUser;

import java.util.Scanner;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

public class UpdateUser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter User ID:");
        int userId = Integer.parseInt(
                sc.nextLine());

        System.out.println("Enter New UserName:");
        String userName = sc.nextLine();

        System.out.println("Enter New Password:");
        String password = sc.nextLine();

        System.out.println("Enter New Email:");
        String email = sc.nextLine();

        System.out.println("Enter New Address:");
        String address = sc.nextLine();

        System.out.println("Enter New Role:");
        String role = sc.nextLine();

        User user = new User();

        user.setUserId(userId);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setRole(role);

        UserDAOImpl udao =
                new UserDAOImpl();

        udao.updateUser(user);

        System.out.println(
                "User Updated Successfully");

        sc.close();
    }
}