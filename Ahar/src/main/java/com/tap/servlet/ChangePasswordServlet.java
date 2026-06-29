package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/changePassword")
public class ChangePasswordServlet
extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        HttpSession session =
                req.getSession();

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        String oldPass =
                req.getParameter(
                        "oldPassword");

        String newPass =
                req.getParameter(
                        "newPassword");

        String confirmPass =
                req.getParameter(
                        "confirmPassword");

        if(user.getPassword()
                .equals(oldPass)
                &&
           newPass.equals(confirmPass)) {

            user.setPassword(
                    newPass);

            new UserDAOImpl()
                    .updateUser(user);

            resp.sendRedirect(
                    "profile");
        }
        else {

            resp.sendRedirect(
                    "changePassword.jsp");
        }
    }
}