package com.tap.servlet;

import java.io.IOException;
import java.util.Map;

import com.tap.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		// User not logged in
		if (session == null ||
				session.getAttribute("loggedInUser") == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		@SuppressWarnings("unchecked")
		Map<Integer, Cart> cartMap =
		(Map<Integer, Cart>) session.getAttribute("cart");

		// Cart Empty
		if (cartMap == null || cartMap.isEmpty()) {

			resp.sendRedirect("cart");
			return;
		}

		// Send cart to JSP
		req.setAttribute("cartMap", cartMap);

		// Forward to checkout page
		req.getRequestDispatcher("/checkout.jsp")
		.forward(req, resp);
	}
}