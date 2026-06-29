package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.CouponDAOImpl;
import com.tap.model.Coupon;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addCoupon")
public class AddCouponServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		Coupon coupon = new Coupon();

		coupon.setCouponCode(
				req.getParameter("couponCode"));

		coupon.setDiscountPercent(
				Double.parseDouble(
						req.getParameter("discount")));

		coupon.setMinAmount(
				Double.parseDouble(
						req.getParameter("minAmount")));

		coupon.setActive(true);

		CouponDAOImpl dao =
				new CouponDAOImpl();

		dao.addCoupon(coupon);

		resp.sendRedirect("couponManagement");
	}
}