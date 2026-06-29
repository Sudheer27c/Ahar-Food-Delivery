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

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if (session == null ||
				session.getAttribute("loggedInUser") == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		try {

			String action =
					req.getParameter("action");

			String menuIdStr =
					req.getParameter("menuId");

			if (action == null ||
					menuIdStr == null) {

				resp.sendRedirect("cart");
				return;
			}

			int menuId =
					Integer.parseInt(menuIdStr);

			Map<Integer, Cart> cartMap =
					(Map<Integer, Cart>)
					session.getAttribute("cart");

			if (cartMap == null ||
					cartMap.isEmpty()) {

				resp.sendRedirect("cart");
				return;
			}

			Cart item =
					cartMap.get(menuId);

			if (item != null) {

				switch (action) {

				case "increase":

					item.setQuantity(
							item.getQuantity() + 1);
					break;

				case "decrease":

					if (item.getQuantity() > 1) {

						item.setQuantity(
								item.getQuantity() - 1);

					} else {

						cartMap.remove(menuId);
					}

					break;

				case "remove":

					cartMap.remove(menuId);
					break;
				}
			}

			session.setAttribute(
					"cart",
					cartMap);

			// UPDATE CART COUNT

			int cartCount = 0;

			for (Cart c : cartMap.values()) {

				cartCount += c.getQuantity();
			}

			session.setAttribute(
					"cartCount",
					cartCount);

			resp.sendRedirect("cart");

		}
		catch (Exception e) {

			e.printStackTrace();

			resp.sendRedirect("cart");
		}
	}
}