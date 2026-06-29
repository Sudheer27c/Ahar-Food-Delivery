package com.tap.utility.LaunchMenu;

import java.util.List;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

public class GetAllMenus {

    public static void main(String[] args) {

        MenuDAOImpl dao = new MenuDAOImpl();

        List<Menu> menus = dao.getAllMenus();

        if(menus.isEmpty()) {
            System.out.println("No Menus Found");
        }
        else {
            for(Menu menu : menus) {
                System.out.println(menu);
            }
        }
    }
}