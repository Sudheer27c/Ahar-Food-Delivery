package com.tap.utility.LaunchMenu;

import java.util.Scanner;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

public class UpdateMenu {


public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter Menu ID:");
    int menuId = sc.nextInt();

    System.out.println("Enter Restaurant ID:");
    int restaurantId = sc.nextInt();
    sc.nextLine();

    System.out.println("Enter Item Name:");
    String itemName = sc.nextLine();

    System.out.println("Enter Description:");
    String description = sc.nextLine();

    System.out.println("Enter Price:");
    double price = sc.nextDouble();

    System.out.println("Enter Rating:");
    double rating = sc.nextDouble();

    System.out.println("Is Available (true/false):");
    boolean isAvailable = sc.nextBoolean();
    sc.nextLine();

    System.out.println("Enter Image Path:");
    String imagePath = sc.nextLine();

    Menu menu = new Menu(
            menuId,
            restaurantId,
            itemName,
            description,
            price,
            isAvailable,
            imagePath,
            rating
    );

    MenuDAOImpl dao =
            new MenuDAOImpl();

    dao.updateMenu(menu);

    System.out.println(
            "Menu Updated Successfully");

    sc.close();
}


}
