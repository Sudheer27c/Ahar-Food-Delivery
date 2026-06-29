package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderTable;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/manageOrders")
public class ManageOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		OrderTableDAOImpl dao =
				new OrderTableDAOImpl();

		List<OrderTable> orders =
				dao.getAllOrders();

		req.setAttribute(
				"orders",
				orders);

		req.getRequestDispatcher(
				"manageOrders.jsp")
		.forward(req, resp);
	}
}