package com.tap.utility.LaunchMenu;

import java.util.Scanner;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

public class GetMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Menu ID:");
        int menuId = sc.nextInt();

        MenuDAOImpl dao = new MenuDAOImpl();

        Menu menu = dao.getMenu(menuId);

        if(menu != null) {
            System.out.println(menu);
        }
        else {
            System.out.println("Menu Not Found");
        }

        sc.close();
    }
}