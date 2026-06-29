package com.tap.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

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

		req.getRequestDispatcher("/cart.jsp")
		.forward(req, resp);
	}

	@Override
	protected void doPost(
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

			int menuId =
					Integer.parseInt(
							req.getParameter("menuId"));

			MenuDAOImpl dao =
					new MenuDAOImpl();

			Menu menu =
					dao.getMenu(menuId);

			if (menu == null) {

				resp.sendRedirect("home");
				return;
			}

			Map<Integer, Cart> cartMap =
					(Map<Integer, Cart>)
					session.getAttribute("cart");

			if (cartMap == null) {

				cartMap = new HashMap<>();
			}

			int newRestaurantId =
					menu.getRestaurantId();

			// CHECK DIFFERENT RESTAURANT

			if (!cartMap.isEmpty()) {

				int existingRestaurantId =
						cartMap.values()
						.iterator()
						.next()
						.getRestaurantId();

				if (existingRestaurantId != newRestaurantId) {

					session.setAttribute(
							"newMenuId",
							menuId);

					resp.sendRedirect(
							"restaurantChangePopup.jsp");

					return;
				}
			}

			// ITEM ALREADY EXISTS

			if (cartMap.containsKey(menuId)) {

				Cart item =
						cartMap.get(menuId);

				item.setQuantity(
						item.getQuantity() + 1);

			} else {

				Cart item =
						new Cart(
								menu.getMenuId(),
								menu.getRestaurantId(),
								menu.getItemName(),
								menu.getPrice(),
								1,
								menu.getImagePath()
								);

				cartMap.put(
						menuId,
						item);
			}

			session.setAttribute(
					"cart",
					cartMap);

			updateCartCount(session, cartMap);

			resp.sendRedirect("cart");

		}
		catch (Exception e) {

			e.printStackTrace();

			resp.sendRedirect("home");
		}
	}

	private void updateCartCount(
			HttpSession session,
			Map<Integer, Cart> cartMap) {

		int cartCount = 0;

		for (Cart c : cartMap.values()) {

			cartCount += c.getQuantity();
		}

		session.setAttribute(
				"cartCount",
				cartCount);
	}
}