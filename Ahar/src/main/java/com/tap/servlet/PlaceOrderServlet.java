package com.tap.servlet;

import java.io.IOException;
import com.tap.DAOImpl.CouponDAOImpl;
import com.tap.model.Coupon;
import java.sql.Timestamp;
import java.util.Map;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.Cart;
import com.tap.model.OrderItem;
import com.tap.model.OrderTable;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		resp.sendRedirect("checkout");
	}

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if (session == null) {

			resp.sendRedirect("login.jsp");
			return;
		}

		User user =
				(User) session.getAttribute(
						"loggedInUser");

		Map<Integer, Cart> cart =
				(Map<Integer, Cart>)
				session.getAttribute("cart");

		if (user == null ||
				cart == null ||
				cart.isEmpty()) {

			resp.sendRedirect("home");
			return;
		}

		try {

			double total = 0;

			for (Cart c : cart.values()) {

				total += c.getTotalPrice();
			}

			// ---------------- Coupon ----------------

			Coupon coupon =
					(Coupon) session.getAttribute("coupon");

			double discount = 0;

			if (coupon != null &&
					coupon.isActive() &&
					total >= coupon.getMinAmount()) {

				discount =
						total *
						coupon.getDiscountPercent()
						/ 100;
			}
			// Final Bill

			double deliveryFee = 40;

			double gst =
					(total - discount) * 0.05;

			double finalAmount =
					total
					- discount
					+ deliveryFee
					+ gst;

			String paymentMethod =
					req.getParameter(
							"paymentMethod");

			if (paymentMethod == null ||
					paymentMethod.trim().isEmpty()) {

				paymentMethod =
						"Cash On Delivery";
			}

			int restaurantId =
					cart.values()
					.iterator()
					.next()
					.getRestaurantId();

			System.out.println(
					"User ID = "
							+ user.getUserId());

			System.out.println(
					"Cart Size = "
							+ cart.size());

			System.out.println(
					"Restaurant ID = "
							+ restaurantId);

			OrderTable order =
					new OrderTable();

			order.setUserId(
					user.getUserId());

			order.setRestaurantId(
					restaurantId);

			order.setOrderDate(
					new Timestamp(
							System.currentTimeMillis()));

			order.setTotalAmount(finalAmount);

			// IMPORTANT
			order.setStatus(
					"PENDING");

			order.setPaymentMethod(
					paymentMethod);

			OrderTableDAOImpl orderDAO =
					new OrderTableDAOImpl();

			int orderId =
					orderDAO.addOrder(order);

			System.out.println(
					"Generated Order ID = "
							+ orderId);

			if (orderId <= 0) {

				resp.sendRedirect(
						"checkout?error=orderfailed");

				return;
			}

			OrderItemDAOImpl itemDAO =
					new OrderItemDAOImpl();

			for (Cart c : cart.values()) {

				OrderItem item =
						new OrderItem();

				item.setOrderId(
						orderId);

				item.setMenuId(
						c.getMenuId());

				item.setQuantity(
						c.getQuantity());

				item.setItemTotal(
						c.getTotalPrice());

				itemDAO.addOrderItem(
						item);
			}

			session.setAttribute(
					"lastOrderId",
					orderId);

			session.removeAttribute("coupon");

			session.removeAttribute(
					"cart");

			session.setAttribute(
					"cartCount",
					0);

			resp.sendRedirect(
					"orderSuccess.jsp");

		}
		catch (Exception e) {

			e.printStackTrace();

			resp.sendRedirect(
					"checkout?error=somethingwentwrong");
		}
	}
}