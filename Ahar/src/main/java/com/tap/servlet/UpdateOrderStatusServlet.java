package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.OrderTableDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		int orderId =
				Integer.parseInt(
						req.getParameter(
								"orderId"));

		String status =
				req.getParameter(
						"status");

		OrderTableDAOImpl dao =
				new OrderTableDAOImpl();

		dao.updateOrderStatus(
				orderId,
				status);

		resp.sendRedirect(
				"adminOrders");
	}
}