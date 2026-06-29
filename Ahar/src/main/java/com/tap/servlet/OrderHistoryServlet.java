package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.OrderTableDAOImpl;
import com.tap.model.OrderTable;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session =
				req.getSession(false);

		if(session == null){

			resp.sendRedirect("login.jsp");
			return;
		}

		User user =
				(User)session.getAttribute(
						"loggedInUser");

		if(user == null){

			resp.sendRedirect("login.jsp");
			return;
		}

		try{

			OrderTableDAOImpl dao =
					new OrderTableDAOImpl();

			List<OrderTable> orders =
					dao.getOrdersByUserId(
							user.getUserId());

			req.setAttribute(
					"orders",
					orders);

			req.getRequestDispatcher(
					"orderHistory.jsp")
			.forward(req, resp);

		}
		catch(Exception e){

			e.printStackTrace();

			req.setAttribute(
					"errorMessage",
					"Unable to load order history");

			req.getRequestDispatcher(
					"error.jsp")
			.forward(req, resp);
		}
	}
	

}
