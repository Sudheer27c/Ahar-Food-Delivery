package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        String email =
                req.getParameter("email");

        String password =
                req.getParameter("newPassword");

        UserDAOImpl dao =
                new UserDAOImpl();

        User user =
                dao.getUserByEmail(email);

        if(user!=null){

            dao.updatePassword(email,password);

            resp.sendRedirect(
                    "login.jsp?success=password");

        }
        else{

            resp.sendRedirect(
                    "forgotPassword.jsp?error=true");
        }
    }
}