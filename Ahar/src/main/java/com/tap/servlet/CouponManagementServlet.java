package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAOImpl.CouponDAOImpl;
import com.tap.model.Coupon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/couponManagement")
public class CouponManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		CouponDAOImpl dao =
				new CouponDAOImpl();

		String keyword =
				req.getParameter("keyword");

		List<Coupon> coupons;

		if (keyword == null || keyword.trim().isEmpty()) {

			coupons =
					dao.getAllCoupons();

		} else {

			coupons =
					dao.searchCoupon(keyword);
		}

		req.setAttribute("coupons", coupons);

		req.getRequestDispatcher("couponManagement.jsp")
		.forward(req, resp);
	}
}