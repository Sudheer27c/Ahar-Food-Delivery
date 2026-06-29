package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.CouponDAOImpl;
import com.tap.model.Coupon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/applyCoupon")
public class ApplyCouponServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		HttpSession session = req.getSession();

		String couponCode = req.getParameter("couponCode");

		// Remove old messages
		session.removeAttribute("couponMessage");
		session.removeAttribute("coupon");

		// Empty coupon
		if (couponCode == null || couponCode.trim().isEmpty()) {

			session.setAttribute(
					"couponMessage",
					"Please enter a coupon code.");

			resp.sendRedirect("checkout");
			return;
		}

		CouponDAOImpl dao = new CouponDAOImpl();

		Coupon coupon =
				dao.getCouponByCode(
						couponCode.trim());

		if (coupon == null) {

			session.setAttribute(
					"couponMessage",
					"Invalid Coupon Code!");

		}
		else if (!coupon.isActive()) {

			session.setAttribute(
					"couponMessage",
					"This coupon is inactive.");

		}
		else {

			session.setAttribute(
					"coupon",
					coupon);

			session.setAttribute(
					"couponMessage",
					"Coupon Applied Successfully!");
		}

		resp.sendRedirect("checkout");
	}
}