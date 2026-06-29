package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.OrderTableDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancelOrder")
public class CancelOrderServlet
extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		int orderId =
				Integer.parseInt(
						req.getParameter("orderId"));

		OrderTableDAOImpl dao =
				new OrderTableDAOImpl();

		dao.updateOrderStatus(
				orderId,
				"CANCELLED");

		resp.sendRedirect(
				"orderHistory");
	}
}