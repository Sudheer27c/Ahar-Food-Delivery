package com.tap.servlet;
import com.tap.model.Restaurant;
import com.tap.DAOImpl.RestaurantDAOImpl;
import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderItem;
import com.tap.model.OrderTable;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if(session == null ||
				session.getAttribute("loggedInUser") == null){

			resp.sendRedirect("login.jsp");
			return;
		}

		int orderId =
				Integer.parseInt(
						req.getParameter("orderId"));

		OrderTableDAOImpl orderDAO =
				new OrderTableDAOImpl();

		OrderTable order =
				orderDAO.getOrder(orderId);

		OrderItemDAOImpl itemDAO =
				new OrderItemDAOImpl();

		List<OrderItem> orderItems =
				itemDAO.getOrderItemsByOrderId(
						orderId);
		RestaurantDAOImpl restaurantDAO =
				new RestaurantDAOImpl();

		Restaurant restaurant =
				restaurantDAO.getRestaurant(
						order.getRestaurantId());

		req.setAttribute(
				"restaurant",
				restaurant);

		req.setAttribute(
				"order",
				order);

		req.setAttribute(
				"orderItems",
				orderItems);

		req.getRequestDispatcher(
				"/orderDetails.jsp")
		.forward(req, resp);
	}
}