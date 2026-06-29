package com.tap.servlet;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/adminHome")
public class AdminHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        HttpSession session =
                req.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null) {

            resp.sendRedirect("login.jsp");
            return;
        }

        resp.sendRedirect("adminHome.jsp");
    }
}