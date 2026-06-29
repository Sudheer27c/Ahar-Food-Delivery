package com.tap.utility.LaunchUser;

import java.util.Scanner;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

public class AddUser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Enter User Name:");
            String userName = sc.nextLine();

            System.out.println("Enter Password:");
            String password = sc.nextLine();

            System.out.println("Enter Email:");
            String email = sc.nextLine();

            System.out.println("Enter Address:");
            String address = sc.nextLine();

            System.out.println("Enter Role (admin/customer):");
            String role = sc.nextLine();

            User user = new User();

            user.setUsername(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setAddress(address);
            user.setRole(role);

            UserDAOImpl dao = new UserDAOImpl();

            int result = dao.addUser(user);

            if(result > 0) {

                System.out.println(
                        "User Added Successfully");

                System.out.println(user);
            }
            else {

                System.out.println(
                        "Failed To Add User");
            }

        }
        catch(Exception e) {

            e.printStackTrace();
        }
        finally {

            sc.close();
        }
    }
}