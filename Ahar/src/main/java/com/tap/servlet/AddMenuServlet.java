package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        Menu menu =
                new Menu();

        menu.setRestaurantId(
                Integer.parseInt(
                        req.getParameter(
                                "restaurantId")));

        menu.setItemName(
                req.getParameter(
                        "itemName"));

        menu.setDescription(
                req.getParameter(
                        "description"));

        menu.setPrice(
                Double.parseDouble(
                        req.getParameter(
                                "price")));

        menu.setAvailable(
                true);

        menu.setImagePath(
                req.getParameter(
                        "imagePath"));

        MenuDAOImpl dao =
                new MenuDAOImpl();

        dao.addMenu(menu);

        resp.sendRedirect(
                "menuManagement");
    }
}