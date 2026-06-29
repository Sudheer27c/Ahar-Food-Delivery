package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.MenuDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteMenu")
public class DeleteMenuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        int menuId =
                Integer.parseInt(
                        req.getParameter("menuId"));

        MenuDAOImpl dao =
                new MenuDAOImpl();

        dao.deleteMenu(menuId);

        resp.sendRedirect(
                "menuManagement");
    }
}