package com.tap.utility.LaunchMenu;

import java.util.Scanner;

import com.tap.DAOImpl.MenuDAOImpl;

public class DeleteMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Menu ID:");
        int menuId = sc.nextInt();

        MenuDAOImpl dao = new MenuDAOImpl();

        dao.deleteMenu(menuId);

        System.out.println("Menu Deleted Successfully");

        sc.close();
    }
}