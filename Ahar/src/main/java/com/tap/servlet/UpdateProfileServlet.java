package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session =
                req.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            resp.sendRedirect("login.jsp");
            return;
        }

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        user.setUsername(
                req.getParameter("username"));

        user.setEmail(
                req.getParameter("email"));

        user.setAddress(
                req.getParameter("address"));

        UserDAOImpl dao =
                new UserDAOImpl();

        dao.updateUser(user);

        session.setAttribute(
                "loggedInUser",
                user);

        resp.sendRedirect(
                "profile");
    }
}