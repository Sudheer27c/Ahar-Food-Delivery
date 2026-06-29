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

@WebServlet("/confirmRestaurantChange")
public class ConfirmRestaurantChangeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession();

		Integer menuId =
				(Integer) session.getAttribute("newMenuId");

		if(menuId == null) {

			resp.sendRedirect("home");
			return;
		}

		MenuDAOImpl dao =
				new MenuDAOImpl();

		Menu menu =
				dao.getMenu(menuId);

		Map<Integer, Cart> cartMap =
				new HashMap<>();

		Cart item =
				new Cart(
						menu.getMenuId(),
						menu.getRestaurantId(),
						menu.getItemName(),
						menu.getPrice(),
						1,
						menu.getImagePath());

		cartMap.put(
				menu.getMenuId(),
				item);

		session.setAttribute(
				"cart",
				cartMap);

		session.setAttribute(
				"cartCount",
				1);

		session.removeAttribute(
				"newMenuId");

		resp.sendRedirect("cart");
	}
}