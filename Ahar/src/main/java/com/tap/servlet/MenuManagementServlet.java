package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/menuManagement")
public class MenuManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        MenuDAOImpl dao =
                new MenuDAOImpl();

        List<Menu> menus =
                dao.getAllMenus();

        req.setAttribute(
                "menus",
                menus);

        req.getRequestDispatcher(
                "menuManagement.jsp")
                .forward(req, resp);
    }
}